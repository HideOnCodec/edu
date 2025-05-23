package com.edu.todayperfume.user.service;

import com.edu.todayperfume.user.dto.UserRequestDto;
import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.dto.UserLoginRequestDto;

public interface UserService {
    UserDto create(UserRequestDto req);
    UserDto updatePassword(String password);
    void login(UserLoginRequestDto req);
    void logout();
    void delete();
}
