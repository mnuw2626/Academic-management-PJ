package com.academic.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDateDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
