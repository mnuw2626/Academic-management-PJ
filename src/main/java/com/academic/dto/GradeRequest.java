package com.academic.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class GradeRequest {
    // 성적들 목록을 저장하기 위함
    private List<EnrollDTO> grades;
}
