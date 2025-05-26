package com.edu.todayperfume.perfume.mapper;

import com.edu.todayperfume.perfume.dto.PerfumeRecommendReqDto;
import com.edu.todayperfume.perfume.dto.TypeRankDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendHistoryMapper {
    void save(@Param("perfumeId") Long perfumeId, @Param("req") PerfumeRecommendReqDto req);
    List<TypeRankDto> rank();
}
