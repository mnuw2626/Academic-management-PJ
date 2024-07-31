package com.academic.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class StdDTO extends UserDTO{
    private int stdNo;
    private String name;
    private int collegeId;
    private int deptId;
    private int grade;
    private int semester;
    private TuitionDTO tuitionDTO;
}
