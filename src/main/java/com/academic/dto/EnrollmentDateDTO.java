package com.academic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EnrollmentDateDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
