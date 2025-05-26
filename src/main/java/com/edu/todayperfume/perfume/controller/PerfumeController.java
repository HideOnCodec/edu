package com.edu.todayperfume.perfume.controller;

import com.edu.todayperfume.global.LoginUtil;
import com.edu.todayperfume.note.dto.NotesDto;
import com.edu.todayperfume.perfume.dto.*;
import com.edu.todayperfume.note.service.NotesService;
import com.edu.todayperfume.perfume.service.PerfumeReadService;
import com.edu.todayperfume.perfume.service.PerfumeService;
import com.edu.todayperfume.review.dto.ReviewDto;
import com.edu.todayperfume.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/perfume")
public class PerfumeController {
    private final PerfumeReadService perfumeReadService;
    private final PerfumeService perfumeService;
    private final ReviewService reviewService;
    private final NotesService notesService;

    /**
     * 향수 상세 정보 조회 View
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String perfume(@PathVariable("id") Long id, Model model) {
        String loginUser = LoginUtil.getLoginUser();
        boolean isAdmin = perfumeService.isAdmin(loginUser);
        PerfumeDto result = perfumeReadService.findPerfumeById(id);
        List<ReviewDto> reviewList = reviewService.findReviewListAllOrderByCreatedAt(id);
        Optional<ReviewDto> existedReviewOpt = reviewService.findReviewByUserIdAndPerfumeId(loginUser, id);
        model.addAttribute("perfume", result);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("existedReview", existedReviewOpt.orElse(null));
        return "perfume/detail";
    }

    /**
     * 향수 목록 조회 기능
     * @param sort (last, rate)
     * @param noteId
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "sort", defaultValue = "last") String sort, @RequestParam(value = "noteId", defaultValue = "0") Long noteId, Model model) {
        boolean isAdmin = perfumeService.isAdmin(LoginUtil.getLoginUser());
        model.addAttribute("noteList", notesService.findNotesListAll());
        List<PerfumeDto> result = switch (sort.toLowerCase()){
            case "last" -> perfumeReadService.findPerfumeListByNoteOrderByCreatedAt(noteId);
            case "rate" -> perfumeReadService.findPerfumeListByNoteOrderByRating(noteId);
            default -> perfumeReadService.findPerfumeListByNoteOrderByCreatedAt(noteId);
        };
        model.addAttribute("sort", sort);
        model.addAttribute("noteId", noteId);
        model.addAttribute("perfumeList", result);
        model.addAttribute("isAdmin", isAdmin);
        return "perfume/list";
    }

    /**
     * 향수 추천 뷰
     * @param model
     * @return
     */
    @GetMapping("/recommend")
    public String recommendView(Model model){
        List<TypeDto> typeList = perfumeReadService.findTypeList();
        model.addAttribute("typeList", typeList);
        return "perfume/recommend";
    }

    /**
     * 향수 추천 기능
     * @param req
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/recommend")
    public String recommend(@ModelAttribute PerfumeRecommendReqDto req, RedirectAttributes redirectAttributes){
        List<NotesDto> noteList = notesService.findNotesListByType(req.type1(), req.type2());
        PerfumeDto result = perfumeReadService.recommend(req, noteList);
        
        if(result == null){
            redirectAttributes.addFlashAttribute("error", "향수 추천 실패. 다시 시도해주세요.");
        } else {
            redirectAttributes.addFlashAttribute("perfume", result);
        }
        
        return "redirect:/perfume/recommend";
    }

    /**
     * 향수 생성 뷰
     * @return
     */
    @GetMapping("/create")
    public String createForm(Model model){
        List<NotesDto> noteList = notesService.findNotesListAll();
        model.addAttribute("noteList", noteList);
        return "perfume/create";
    }

    /**
     * 향수 생성 기능
     * @param req
     * @return
     */
    @PostMapping("/create")
    public String create(@ModelAttribute PerfumeCreateReqDto req, @RequestParam("image") MultipartFile image) throws IOException {
        String imageUrl = perfumeService.fileUpload(image);
        perfumeService.createPerfume(imageUrl, req);
        return "redirect:/perfume/list";
    }

    /**
     * 향수 수정 뷰
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long perfumeId, Model model){
        List<NotesDto> noteList = notesService.findNotesListAll();
        model.addAttribute("noteList", noteList);
        model.addAttribute("perfume", perfumeReadService.findPerfumeById(perfumeId));
        return "perfume/update";
    }

    /**
     * 향수 수정 기능
     * @param req
     * @return
     */
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute PerfumeUpdateReqDto req, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        String imageUrl = null;
        if(image != null && !image.isEmpty()){
            imageUrl = perfumeService.fileUpload(image);
        }
        perfumeService.updatePerfume(imageUrl, req, id);
        return "redirect:/perfume/" + req.id();
    }

    /**
     * 향수 삭제 기능
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        PerfumeDto perfumeDto = perfumeReadService.findPerfumeById(id);
        perfumeService.deleteFile(perfumeDto.image());
        perfumeService.deletePerfume(id);
        return "redirect:/perfume/list";
    }

    /**
     * 향수 통계 뷰
     */
    @GetMapping("/summary")
    public String summary(Model model){
        List<TypeRankDto> typeList = perfumeReadService.findTypeRankList();
        List<PerfumeRankDto> perfumeRankList = perfumeReadService.findPerfumeRankList();
        model.addAttribute("typeList", typeList);
        model.addAttribute("perfumeRankList", perfumeRankList);
        return "perfume/summary";
    }

}
