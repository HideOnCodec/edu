package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.perfume.dto.PerfumeCreateReqDto;
import com.edu.todayperfume.perfume.dto.PerfumeUpdateReqDto;

public interface PerfumeService {
    void createPerfume(PerfumeCreateReqDto req);
    void updatePerfume(PerfumeUpdateReqDto rep, Long id);
    void deletePerfume(Long id);
    boolean isAdmin(String loginUser);
}
