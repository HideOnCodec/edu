package com.edu.todayperfume.user.mapper;

import com.edu.todayperfume.user.dto.UserRequestDto;
import com.edu.todayperfume.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(UserRequestDto userRequestDto);
    Optional<UserDto> findById(@Param("id") String id);
    Optional<UserDto> findByIdAndPassword(@Param("id") String id, @Param("password") String password);
    void updatePassword(@Param("id") String id, @Param("password") String password);
    void updateIsDeleted(@Param("id") String id);
}
