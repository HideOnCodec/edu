package com.edu.todayperfume.user.service;

import com.edu.todayperfume.global.LoginUtil;
import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.user.dto.UserRequestDto;
import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.dto.UserLoginRequestDto;
import com.edu.todayperfume.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    /**
     * user 회원가입
     * @param req
     */
    @Override
    public UserDto create(UserRequestDto req) {
        // 중복 아이디 검증
        boolean isDuplicate = userMapper.findById(req.id()).isPresent();
        if (isDuplicate) {
            throw new CustomException(BaseCode.DUPLICATE_USER_ID);
        }

        userMapper.save(req);

        return userMapper.findById(req.id()).get();
    }

    @Override
    public UserDto updatePassword(String password) {
        String loginUserId = LoginUtil.getLoginUser();
        // 로그인한 유저가 아닐 경우 권한이 없음
        if(loginUserId == null){
            throw new CustomException(BaseCode.NOT_AUTHORIZED_USER);
        }

        userMapper.updatePassword(new UserRequestDto(loginUserId, password));
        UserDto updatedUser = userMapper.findById(loginUserId).get();
        return updatedUser;
    }

    /**
     * user 로그인 인증 처리
     * @param req
     */
    @Override
    public void login(UserLoginRequestDto req) {
        // 유저가 존재하는지 확인
        Optional<UserDto> user = userMapper.findByIdAndPassword(req.id(), req.password());
        // 존재하지 않는 유저일 경우 로그인 실패
        if(user.isEmpty() || user.get().isDeleted()){
            throw new CustomException(BaseCode.FAIL_LOGIN);
        }

        // 전역 hashMap에 로그인한 유저 정보 저장(인증 처리)
        LoginUtil.setLoginUser(req.id());
    }

    /**
     * user 로그아웃 처리
     */
    @Override
    public void logout() {
        // 로그인한 유저가 아닐 경우 예외 던지기
        if (LoginUtil.getLoginUser() == null) {
            throw new CustomException(BaseCode.FAIL_LOGOUT);
        }

        LoginUtil.clearLoginUser();
    }

    @Override
    public void delete() {
        userMapper.updateIsDeleted(LoginUtil.getLoginUser());
    }


}
