package com.academic.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class LectureDTO {
    private int no; //순번
    private int code; //과목코드
    private String name; //과목명
    private int year; //개설년도
    private int grade; //학년
    private int semester; //학기
    private int credit; //학점
    private String type; //타입 - 전공 / 교양
    private String professor; //담당 교수 이름
    private String week; // 요일
    private LocalTime starTime; //강의 시작 시간
    private LocalTime endTime; //강의 끝나는 시간
    private String room; //강의실
    private int capacity; //정원
    private Integer numOfStudent; //지원자 수
    private String report; //강의계획서
}
