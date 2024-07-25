package com.academic.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StdDTO {
    private int stdNo;
    private String name;
    private int collegeId;
    private int deptId;
    private int grade;
    private int semester;
}
