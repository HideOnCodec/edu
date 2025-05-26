package com.edu.todayperfume.perfume.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.edu.todayperfume.global.LoginUtil;
import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.global.exception.NotLoginUserException;
import com.edu.todayperfume.perfume.dto.PerfumeCreateReqDto;
import com.edu.todayperfume.perfume.dto.PerfumeDto;
import com.edu.todayperfume.perfume.dto.PerfumeUpdateReqDto;
import com.edu.todayperfume.perfume.mapper.PerfumeMapper;
import com.edu.todayperfume.user.dto.UserDto;
import com.edu.todayperfume.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.region.static}")
    private String region;
    private final PerfumeMapper perfumeMapper;
    private final UserMapper userMapper;
    private final AmazonS3 amazonS3;
    /**
     * 향수 정보 생성 후 DB에 저장
     * @param req
     */
    @Override
    public void createPerfume(String image, PerfumeCreateReqDto req) {
        // 향수 데이터 생성
        log.info("createPerfume() :: {}", req.name());
        // 권한 체크
        String loginUser = LoginUtil.getLoginUser();
        checkAuth(loginUser);

        perfumeMapper.save(image, req, loginUser);
    }

    /**
     * 향수 정보 수정 후 DB에 저장
     * @param req
     */
    @Override
    public void updatePerfume(String image, PerfumeUpdateReqDto req, Long id) {
        log.info("updatePerfume() :: {}", req.id());
        // 권한 체크
        String loginUser = LoginUtil.getLoginUser();
        checkAuth(loginUser);
        perfumeMapper.update(image, req, id, loginUser);
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

    @Override
    public String fileUpload(MultipartFile multipartFile) throws IOException {
        log.info("fileUpload() :: {}", multipartFile.getOriginalFilename());
        try{
            if(!multipartFile.isEmpty()) {
                // 파일 이름의 중복을 막기 위해 "UUID(랜덤 값) + 원본파일이름"로 연결함
                String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

                // 파일 사이즈를 ContentLength를 이용하여 S3에 알려줌
                ObjectMetadata objMeta = new ObjectMetadata();
                // url을 클릭 시 사진이 웹에서 보이는 것이 아닌 바로 다운되는 현상을 해결하기 위해 메타데이터 타입 설정
                objMeta.setContentType(multipartFile.getContentType());
                InputStream inputStream = multipartFile.getInputStream();
                if(inputStream != null) {
                    objMeta.setContentLength(inputStream.available());
                }

                // 파일 stream을 열어서 S3에 파일을 업로드
                amazonS3.putObject(bucket, s3FileName, inputStream, objMeta);
                inputStream.close();

                // Url 가져와서 반환
                log.info("S3 upload file name = {}",s3FileName);
                return amazonS3.getUrl(bucket, s3FileName).toString();
            }
        }catch (AmazonS3Exception e) {
            throw new AmazonS3Exception("이미지 업로드를 실패했습니다.", e);
        }
        return null;
    }

    @Override
    public void deleteFile(String image) {
        log.info("deleteFile() :: {}", image);
        if(image == null || image.isBlank()){
            return;
        }
        try {
            String bucketUrl = "https://s3."+region+".amazonaws.com/"+bucket;
            String fileName = image.substring(bucketUrl.length() + 1);
            DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
            amazonS3.deleteObject(request);
        }
        catch (AmazonS3Exception e) {
            throw new AmazonS3Exception("이미지 삭제를 실패했습니다.", e);
        }
    }

    // 관리자인지 체크하는 메서드
    @Override
    public boolean isAdmin(String loginUser) {
        if(loginUser == null || loginUser.isEmpty()) {
            return false;
        }
        UserDto user = userMapper.findById(loginUser).orElseThrow(() -> new CustomException(BaseCode.NOT_EXISTED_EXCEPTION));
        return user.userType().equals("ADMIN");
    }

    public void checkAuth(String loginUser){
        if(loginUser == null) {
            throw new NotLoginUserException();
        }
        else if(!isAdmin(loginUser)) {
            throw new CustomException(BaseCode.NOT_AUTHORIZED_USER);
        }
    }
}
