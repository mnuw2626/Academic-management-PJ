package com.academic.dto;

import lombok.*;

@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StdDTO {
    private String std_no;
    private String name;
    private String college_id;
    private String dept_id;
    private String grade;
    private String semester;
}
