package com.academic.service;


import com.academic.dto.EnrollmentDateDTO;
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

    private EnrollmentDateDTO currentPeriod = new EnrollmentDateDTO();


    // 전체 강의 조회
    public List<LectureDTO> get_all_lectures() {
        return enrollInCourseMapper.select_all_lectures();
    }

    public EnrollmentDateDTO get_current_period() {
        return currentPeriod;
    }

    public void set_enrollDate(LocalDate startDate, LocalDate endDate) {
        currentPeriod.setStartDate(startDate);
        currentPeriod.setEndDate(endDate);
    }

    //수강신청기간인지 비교
    public Boolean compare_enrollDate_now(LocalDate date){
        LocalDate startDate = currentPeriod.getStartDate();
        LocalDate endDate = currentPeriod.getEndDate();
        return startDate != null && endDate != null && !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
