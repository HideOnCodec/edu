package com.edu.todayperfume.global;

import com.edu.todayperfume.perfume.service.PerfumeReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PerfumeReadService perfumeReadService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
