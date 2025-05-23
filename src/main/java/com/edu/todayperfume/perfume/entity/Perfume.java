package com.edu.todayperfume.perfume.entity;

import com.edu.todayperfume.global.BaseTimeEntity;
import com.edu.todayperfume.user.entity.Gender;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Getter
@SuperBuilder(toBuilder = true)
public class Perfume extends BaseTimeEntity implements Serializable {
    private int id;
    private String name; // 향수 이름
    private Notes topNote; // 탑 노트
    private Notes middleNote; // 미들 노트
    private Notes baseNote; // 베이스 노트
    private Weather weather; // 어울리는 날씨
    private String brand; // 브랜드 이름
    private int price; // 가격
    private Gender gender; // 여성 향수 또는 남성 향수
    private double avgReview; // 평점 평균

    public void printInfo(){
        DecimalFormat df = new DecimalFormat("###,###");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedPrice = df.format(price);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%4d번  ", id))
                .append(String.format("%10s  ", name))
                .append(String.format("%4.1f(점)  ", avgReview))
                .append(String.format("%10s  ", formattedPrice))
                .append(String.format("%10s  ", weather.getName()))
                .append(String.format("%10s    ", brand))
                .append(String.format("%8s  ", gender.equals(Gender.WOMAN) ? "여성" : "남성"))
                .append(String.format("%15s(TOP)  ", topNote.getName()))
                .append(String.format("%18s(MIDDLE)  ", middleNote.getName()))
                .append(String.format("%18s(BASE)  ", baseNote.getName()))
                .append(" 수정일 : " + getUpdatedAt().format(formatter))
                .append(" 수정자 : " + getUpdater());

        System.out.println(sb);
    }
}
