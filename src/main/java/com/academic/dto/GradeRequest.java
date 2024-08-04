package com.academic.dto;

import java.util.List;

public class GradeRequest {
    // 성적들 목록을 저장하기 위함
    private List<EnrollDTO> grades;

    public List<EnrollDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<EnrollDTO> grades) {
        this.grades = grades;
    }

}
