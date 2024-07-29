package com.academic.service;


import com.academic.dto.LectureDTO;
import com.academic.mapper.EnrollInCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

@Service
public class EnrollInCourseService {

    @Autowired
    EnrollInCourseMapper enrollInCourseMapper;


    // 전체 강의 조회
    public List<LectureDTO> get_all_lectures() {
        return enrollInCourseMapper.select_all_lectures();
    }


    //수강신청기간인지 비교
    public Boolean compare_enrollDate_now(LocalDate startDate, LocalDate endDate){
        LocalDate now = LocalDate.now();
        return !now.isBefore(startDate) && !now.isAfter(endDate);
    }
}
