package com.edu.todayperfume.global;

import com.edu.todayperfume.perfume.dto.NotesDto;
import com.edu.todayperfume.perfume.entity.Weather;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RecommendUtil {
    public static Map<Integer, List<NotesDto>> userAnswerToNotesMap = new HashMap<>();

    @Value("${weather.api.key}")
    private static String SECRET_KEY;

    /**
     * 날씨 open API 연동 후 Weather과 매핑
     */
    public static Weather fetchWeatherByOpenAPI(){
        // openAPI 호출 파라미터
        // 한글, 서울, 섭씨 적용
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=Seoul&APPID=" + SECRET_KEY + "&lang=kr&units=metric";

        try {
            // 날씨 정보에 대한 GET 요청
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // JSON 형식의 Response 입력 받기
            BufferedReader reader;
            if (conn.getResponseCode() == 200) { // 상태코드 200일 경우 InputStream을 통해 읽어오기
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else { // 에러 응답 바디 읽기
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;

            // 버퍼에서 계속 값을 읽어 오기
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // JSON 파싱
            JSONObject json = new JSONObject(response.toString());

            double temp = json.getJSONObject("main").getDouble("temp"); // 온도(섭씨)
            String weatherInfo = json.getJSONArray("weather").getJSONObject(0).getString("main"); // 날씨
            String weatherInfoKor = json.getJSONArray("weather").getJSONObject(0).getString("description");
            System.out.println("오늘의 온도 : " + temp + " °C");
            System.out.println("오늘의 날씨 : " + weatherInfoKor);

            return weather(temp, weatherInfo); // 날씨 정보 Weather enum에 매핑

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Weather weather(double temp, String weather){
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
