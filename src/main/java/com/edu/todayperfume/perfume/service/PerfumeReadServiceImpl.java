package com.edu.todayperfume.perfume.service;

import com.edu.todayperfume.global.exception.BaseCode;
import com.edu.todayperfume.global.exception.CustomException;
import com.edu.todayperfume.note.dto.NotesDto;
import com.edu.todayperfume.perfume.dto.*;
import com.edu.todayperfume.perfume.entity.Weather;
import com.edu.todayperfume.perfume.mapper.PerfumeMapper;
import com.edu.todayperfume.perfume.mapper.RecommendHistoryMapper;
import com.edu.todayperfume.perfume.mapper.TypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerfumeReadServiceImpl implements PerfumeReadService {
    private final PerfumeMapper perfumeMapper;
    private final RecommendHistoryMapper recommendHistoryMapper;
    private final TypeMapper typeMapper;

    @Value("${weather.api.key}")
    private String SECRET_KEY;
    private static final String OPENAPI_DOMAIN = "https://api.openweathermap.org";

    /**
     * id에 해당하는 perfume 상세 정보 조회
     * @param id
     */
    @Override
    public PerfumeDto findPerfumeById(Long id) {
        log.info("findPerfumeById() :: {}", id);
        return perfumeMapper.findPerfumeById(id)
                .orElseThrow(() -> new CustomException(BaseCode.NOT_EXISTED_EXCEPTION));
    }

    /**
     * perfume 리스트 최신순 정렬 조회
     */
    @Override
    public List<PerfumeDto> findPerfumeListByNoteOrderByCreatedAt(Long noteId) {
        // DB에서 향수 리스트 가져오기
        log.info("findPerfumeListOrderByCreatedAt() :: noteId = {}", noteId);
        return perfumeMapper.findPerfumeListByNoteOrderByCreatedAt(noteId);
    }

    /**
     * perfume 리스트 별점순 정렬 조회
     */
    @Override
    public List<PerfumeDto> findPerfumeListByNoteOrderByRating(Long noteId) {
        // DB에서 향수 리스트 가져오기
        log.info("findPerfumeListOrderByRating() :: noteId = {}", noteId);
        return perfumeMapper.findPerfumeListByNoteOrderByRating(noteId);
    }

    /**
     * 모든 타입 조회
     */
    @Override
    public List<TypeDto> findTypeList() {
        log.info("findTypeList()");
        return typeMapper.findTypeListAll();
    }

    /**
     * 향수 추천 기능
     */
    @Override
    public PerfumeDto recommend(PerfumeRecommendReqDto req, List<NotesDto> noteList) {
        log.info("recommend() :: type : {} {}", req.type1Name(), req.type2Name());
        
        // 날씨 정보 가져오기
        Weather weather = fetchWeatherByOpenAPI();
        if(weather == null) {
            return null;
        }
        log.info("recommend() :: 날씨 정보 = {}", weather);
        
        req = new PerfumeRecommendReqDto(req.type1(), req.type2(),req.type1Name(), req.type2Name(), weather.name());

        // 추천 리스트 가져오기
        List<PerfumeDto> perfumeList = perfumeMapper.recommend(req, noteList);
        log.info("recommend() :: 추천 리스트 = {}", perfumeList);
        
        if(perfumeList == null || perfumeList.isEmpty()) {
            log.warn("recommend() :: 추천 리스트가 비어있습니다.");
            return null;
        }

        // 랜덤하게 하나 선택
        Random random = new Random();
        int idx = random.nextInt(perfumeList.size());
        PerfumeDto selectedPerfume = perfumeList.get(idx);
        log.info("recommend() :: 최종 선택된 향수 = {}", selectedPerfume);

        // 추천 히스토리에 기록 저장
        recommendHistoryMapper.save(selectedPerfume.id(), req);
        return selectedPerfume;
    }

    /**
     * 선택됐던 취향 정보 조회
     */
    @Override
    public List<TypeRankDto> findTypeRankList() {
        return recommendHistoryMapper.rank();
    }

    /**
     * 성별, 나이대별 향수 탑5
     * @return
     */
    @Override
    public List<PerfumeRankDto> findPerfumeRankList() {
        return perfumeMapper.findPerfumeRankInfo();
    }

    /**
     * 날씨 정보 외부 API 호출
     */
    private Weather fetchWeatherByOpenAPI() {
        try{
            log.info("fetchWeatherByOpenAPI()");

            RestClient restClient = RestClient.builder()
                    .baseUrl(OPENAPI_DOMAIN) // 기본 URL 설정
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // 기본 헤더 설정
                    .build();

            String uri = "/data/2.5/weather?appid="+SECRET_KEY+"&lang=kr&units=metric&q=Seoul";
            WeatherRepDto result = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(WeatherRepDto.class);
            String weather = result.weather().getFirst().main();
            double temp = result.main().temp();
            log.info("fetchWeatherByOpenAPI() :: 온도 : {} °C, 날씨 : {}", temp, weather);

            return weather(temp, weather); // 날씨 정보 Weather enum에 매핑
        }catch (Exception e){
            log.error("fetchWeatherByOpenAPI() :: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private Weather weather(double temp, String weather){
        switch (weather){
            case "Rain" : return Weather.RAINY;
            case "Snow" : return Weather.SNOW;
            case "Clouds" : return Weather.CLOUD;
            default : // 나머지는 온도에 따라 Weather 결정
                if(temp <= 3){
                    return Weather.COLD;
                }
                else if(11 <= temp && temp <= 24){
                    return Weather.SUNNY;
                }
                else{
                    return Weather.HOT;
                }
        }
    }


}
