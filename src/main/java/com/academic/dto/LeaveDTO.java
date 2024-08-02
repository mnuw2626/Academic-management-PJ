package com.academic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LeaveDTO {
    private int stdNo;
    private String name;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate applicationDate;
    private String reason;
    private StdDTO stdDTO;

}
