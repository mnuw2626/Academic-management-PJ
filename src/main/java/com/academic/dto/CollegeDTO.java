package com.academic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CollegeDTO {
    private int id; //단과대학 코드(번호)
    private String name; //단과대학 명
}
