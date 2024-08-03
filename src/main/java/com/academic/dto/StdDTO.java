package com.academic.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
public class StdDTO extends UserDTO{
    private int stdNo; // 학번
    private String name; // 학생이름
    private int collegeId; //단과대학 번호
    private int deptId; // 학과 번호
    private int grade; // 학년
    private int semester; // 학기
    private Integer stdCredit; // 학생이 신청한 학점
}
