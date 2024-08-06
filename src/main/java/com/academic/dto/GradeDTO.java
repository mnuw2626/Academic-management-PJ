package com.academic.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class GradeDTO {
    private String gradeType; //평점(A, A+, A-, B, B+, ...)
    private float gradeValue; //각 평점에 적용될 점수 -> 성적 계산에 사용
}