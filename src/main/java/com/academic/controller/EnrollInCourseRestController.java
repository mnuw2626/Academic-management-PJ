package com.academic.controller;


import com.academic.dto.EnrollmentDateDTO;
import com.academic.dto.LectureDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EnrollInCourseRestController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    // 전체 강의 조회
    @GetMapping("/course/lectures")
    public List<LectureDTO> get_lectures() {
        return enrollInCourseService.get_all_lectures();
    }

    //수강신청기간 비교(현재 날짜가 설정된 시작, 종료 날짜 사이이면 true, 아니면 false 반환 )
    @GetMapping("/course/is/enroll")
    public Boolean get_enroll(){
        LocalDate today = LocalDate.now();
        return enrollInCourseService.compare_enrollDate_now(today);
    }

}
