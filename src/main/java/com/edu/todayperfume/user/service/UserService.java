package com.edu.todayperfume.user.service;

import com.edu.todayperfume.user.dto.UserRequestDto;
import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.dto.LoginRequestDto;

public interface UserService {
    UserDto createUser(UserRequestDto req);
    UserDto updatePassword(String password);
    boolean login(LoginRequestDto req);
    void logout();
    void deleteUser();
}
