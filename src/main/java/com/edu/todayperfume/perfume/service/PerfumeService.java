package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.perfume.dto.PerfumeCreateReqDto;
import com.edu.todayperfume.perfume.dto.PerfumeUpdateReqDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PerfumeService {
    void createPerfume(String image, PerfumeCreateReqDto req);
    void updatePerfume(String image, PerfumeUpdateReqDto rep, Long id);
    void deletePerfume(Long id);
    String fileUpload(MultipartFile file) throws IOException;
    void deleteFile(String image);
    boolean isAdmin(String loginUser);
}
