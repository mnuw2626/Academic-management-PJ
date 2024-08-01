package com.academic.dto;
import lombok.*;

@Getter
@Setter
@ToString
public class EnrollDTO {
    // 수강 내역 DTO
    private int stdNo; //학번
    private int code; //과목코드
    private String grade; //평점(A, A+, A-, B, B+, ... )
}
