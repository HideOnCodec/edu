package com.edu.todayperfume.perfume.mapper;

import com.edu.todayperfume.perfume.dto.TypeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {
    List<TypeDto> findTypeListAll();
}
