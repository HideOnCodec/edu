package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.global.LoginUtil;
import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.global.exception.NotLoginUserException;
import com.edu.todayperfume.perfume.dto.PerfumeCreateReqDto;
import com.edu.todayperfume.perfume.dto.PerfumeUpdateReqDto;
import com.edu.todayperfume.perfume.mapper.PerfumeMapper;
import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeService {
    private final PerfumeMapper perfumeMapper;
    private final UserMapper userMapper;
    /**
     * 향수 정보 생성 후 DB에 저장
     * @param req
     */
    @Override
    public void createPerfume(PerfumeCreateReqDto req) {
        // 향수 데이터 생성
        log.info("createPerfume() :: {}", req.name());
        // 권한 체크
        String loginUser = LoginUtil.getLoginUser();
        checkAuth(loginUser);
        perfumeMapper.save(req, loginUser);
    }

    /**
     * 향수 정보 수정 후 DB에 저장
     * @param req
     */
    @Override
    public void updatePerfume(PerfumeUpdateReqDto req) {
        log.info("updatePerfume() :: {}", req.id());
        // 권한 체크
        String loginUser = LoginUtil.getLoginUser();
        checkAuth(loginUser);
        perfumeMapper.update(req, loginUser);
    }

    /**
     * id에 해당하는 향수 DB에서 삭제
     * @param id
     */
    @Override
    public void deletePerfume(Long id) {
        log.info("deletePerfume() :: {}", id);
        perfumeMapper.delete(id);
    }

    private boolean isAdmin(String loginUser) {
        UserDto user = userMapper.findById(loginUser).orElseThrow(() -> new CustomException(BaseCode.NOT_EXISTED_EXCEPTION));
        return user.userType().equals("ADMIN");
    }

    private void checkAuth(String loginUser){
        if(loginUser == null) {
            throw new NotLoginUserException();
        }
        else if(!isAdmin(loginUser)) {
            throw new CustomException(BaseCode.NOT_AUTHORIZED_USER);
        }
    }
}
