package com.academic.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class DepartmentDTO {
    private int id; //학과 코드(번호)
    private String name; //학과 명
    private int collegeId; //단과대학 코드(번호 / CollegeDTO의 id와 동일)
}
