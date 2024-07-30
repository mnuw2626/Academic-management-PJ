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

    // 날짜를 담을 객체 생성
    private EnrollmentDateDTO currentPeriod = new EnrollmentDateDTO();

    // 전체 강의 조회
    public List<LectureDTO> get_all_lectures() {
        return enrollInCourseMapper.select_all_lectures();
    }

    // 날짜 설정하는 기능
    public void set_enrollDate(LocalDate startDate, LocalDate endDate) {
        currentPeriod.setStartDate(startDate);
        currentPeriod.setEndDate(endDate);
    }

    //현재 날짜와 저장된(관리자-수강신청기간설정페이지에서 설정) 날짜와 비교하여
    // 현재 날짜가 시작 날짜와 종료 날짜 사이에 있으면 true 로 반환
    public Boolean compare_enrollDate_now(LocalDate date){
        LocalDate startDate = currentPeriod.getStartDate();
        LocalDate endDate = currentPeriod.getEndDate();
        return startDate != null && endDate != null && !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    // 날짜 가져오는 함수(생성된 객체 반환)
    public EnrollmentDateDTO get_current_period() {
        return currentPeriod;
    }
}
