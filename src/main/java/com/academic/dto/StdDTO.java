package com.academic.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StdDTO {
    private int std_no;
    private String name;
    private int college_id;
    private int dept_id;
    private int grade;
    private int semester;
}
