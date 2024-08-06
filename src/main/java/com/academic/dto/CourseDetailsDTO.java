package com.academic.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class CourseDetailsDTO {
    private CollegeDTO college; // 단과대학 정보
    private DepartmentDTO department; // 학과 정보
    private LectureDTO lecture; // 강의 정보
    private StdDTO std; // 학생 정보
    private EnrollDTO enroll; // 수강 정보
    private GradeDTO score; //평점정보
}
