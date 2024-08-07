package com.academic.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private int no;
    private String title;
    private String content;
    private LocalDate createdDate;
    private int views;
}
