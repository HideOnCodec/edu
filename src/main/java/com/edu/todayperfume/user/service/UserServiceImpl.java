package com.edu.todayperfume.user.service;

import com.edu.todayperfume.global.LoginUtil;
import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.global.exception.NotLoginUserException;
import com.edu.todayperfume.user.dto.UserRequestDto;
import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.dto.LoginRequestDto;
import com.edu.todayperfume.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    /**
     * user 회원가입
     * @param req
     */
    @Override
    public UserDto createUser(UserRequestDto req) {
        // 중복 아이디 검증
        log.info("createUser() :: userId {}", req.id());
        boolean isDuplicate = userMapper.findById(req.id()).isPresent();
        if (isDuplicate) {
            return null;
        }
        userMapper.save(req);
        log.info("createUser() :: success userId {}", req.id());
        return userMapper.findById(req.id()).get();
    }

    /**
     * 비밀번호 변경
     * @param password
     * @return
     */
    @Override
    public UserDto updatePassword(String password) {
        String loginUserId = LoginUtil.getLoginUser();
        log.info("updatePassword() :: userId {}", loginUserId);
        // 로그인한 유저가 아닐 경우 권한이 없음
        if(loginUserId == null){
            throw new CustomException(BaseCode.NOT_AUTHORIZED_USER);
        }
        // 비밀번호 변경
        userMapper.updatePassword(loginUserId, password);
        log.info("updatePassword() :: success userId {}", loginUserId);
        return userMapper.findById(loginUserId).get();
    }

    /**
     * user 로그인 인증 처리
     * @param req
     */
    @Override
    public boolean login(LoginRequestDto req) {
        log.info("login() :: userId {}", req.id());
        // 유저가 존재하는지 확인
        Optional<UserDto> user = userMapper.findByIdAndPassword(req.id(), req.password());
        // 존재하지 않는 유저일 경우 로그인 실패
        if(user.isEmpty() || user.get().isDeleted() == 1){
            return false;
        }

        // 전역 hashMap에 로그인한 유저 정보 저장(인증 처리)
        LoginUtil.setLoginUser(req.id(), user.get().createdAtStr());
        log.info("login() :: success userId {}", req.id());
        return true;
    }

    /**
     * user 로그아웃 처리
     */
    @Override
    public void logout() {
        log.info("logout() :: userId {}", LoginUtil.getLoginUser());
        // 로그인한 유저가 아닐 경우 예외 던지기
        if (LoginUtil.getLoginUser() == null) {
            throw new NotLoginUserException();
        }

        LoginUtil.clearLoginUser();
        log.info("logout() :: success userId {}", LoginUtil.getLoginUser());
    }

    /**
     * 회원 탈퇴 처리
     */
    @Override
    public void deleteUser() {
        log.info("deleteUser() :: userId {}", LoginUtil.getLoginUser());
        // 논리적 삭제 적용
        userMapper.updateIsDeleted(LoginUtil.getLoginUser());
        LoginUtil.clearLoginUser();
        log.info("deleteUser() :: success userId {}", LoginUtil.getLoginUser());
    }
}
