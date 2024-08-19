package com.academic.dto;

import java.util.List;

public class GradeRequest {
    // 성적들 목록을 저장하기 위함 여러 학생들의 각 과목의 성적을 동시에 등록해야함
    // 성적들이 거쳐가는곳
    private List<EnrollDTO> grades;

    public List<EnrollDTO> getGrades() {
        return grades;
    }

    public void setGrades(List<EnrollDTO> grades) {
        this.grades = grades;
    }

}
