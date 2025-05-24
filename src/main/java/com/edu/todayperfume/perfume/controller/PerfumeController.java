package com.edu.todayperfume.perfume.controller;

import com.edu.todayperfume.note.dto.NotesDto;
import com.edu.todayperfume.perfume.dto.PerfumeDto;
import com.edu.todayperfume.perfume.dto.PerfumeRecommendReqDto;
import com.edu.todayperfume.perfume.dto.TypeDto;
import com.edu.todayperfume.note.service.NotesService;
import com.edu.todayperfume.perfume.service.PerfumeReadService;
import com.edu.todayperfume.review.dto.ReviewDto;
import com.edu.todayperfume.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/perfume")
public class PerfumeController {
    private final PerfumeReadService perfumeReadService;
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
        PerfumeDto result = perfumeReadService.findPerfumeById(id);
        List<ReviewDto> reviewList = reviewService.findReviewListAllOrderByCreatedAt(id);
        model.addAttribute("perfume", result);
        model.addAttribute("reviewList", reviewList);
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
        model.addAttribute("noteList", notesService.findNotesListAll());
        List<PerfumeDto> result = switch (sort.toLowerCase()){
            case "last" -> perfumeReadService.findPerfumeListByNoteOrderByCreatedAt(noteId);
            case "rate" -> perfumeReadService.findPerfumeListByNoteOrderByRating(noteId);
            default -> perfumeReadService.findPerfumeListByNoteOrderByCreatedAt(noteId);
        };
        model.addAttribute("sort", sort);
        model.addAttribute("noteId", noteId);
        model.addAttribute("perfumeList", result);
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

}
