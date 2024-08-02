package com.academic.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@ToString
public class StdEnrollCourseDTO {
    // 수강 내역 정보
    private Integer stdNo; //학번
    private String stdName; //학생이름
    private Integer year; //학년
    private Integer semester; //학기
    private Integer credit; //학점
    private String grade; //평점(A, A+, A-, B, B+, ... )

    private Integer code; //과목코드
    private String lectureName; //과목명
    private String type; //타입 - 전공 / 교양
    private String professor; //담당 교수 이름
    private String week; // 요일
    private LocalTime starTime; //강의 시작 시간
    private LocalTime endTime; //강의 끝나는 시간
    private String room; //강의실

    private Integer capacity; //정원
    private Integer numOfStudent; //지원자 수

    private Boolean status; // 수강 가능한지 여부 -> SQL 쿼리로 0 혹은 1이 되게 함, 1일경우 수강인원이 가득차서 불가능

}
