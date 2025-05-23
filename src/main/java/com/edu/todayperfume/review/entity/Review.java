package com.edu.todayperfume.review.entity;

import com.edu.todayperfume.global.BaseTimeEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder(toBuilder = true)
public class Review extends BaseTimeEntity implements Serializable {
    private int id; // 아이디
    private String content; // 내용
    private int rate; // 평점(1~5)
    private String user; // 작성자
    private int perfumeId;
}
