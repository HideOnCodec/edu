package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.perfume.dto.PerfumeCreateReqDto;
import com.edu.todayperfume.perfume.dto.PerfumeUpdateReqDto;

public interface PerfumeService {
    int create(PerfumeCreateReqDto req);
    void update(int id, PerfumeUpdateReqDto rep);
    void delete(int id);
}
