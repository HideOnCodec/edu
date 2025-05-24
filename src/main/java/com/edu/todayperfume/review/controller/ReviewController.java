package com.edu.todayperfume.review.controller;

import com.edu.todayperfume.review.dto.ReviewCreateReqDto;
import com.edu.todayperfume.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping()
    public String create(@ModelAttribute ReviewCreateReqDto req) {
        reviewService.createReview(req);
        return "redirect:/perfume/" + req.perfumeId();
    }

    @DeleteMapping()
    public String delete(@RequestParam(value = "perfumeId") Long perfumeId, @RequestParam(value = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/perfume/" + perfumeId;
    }
}
