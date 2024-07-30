package com.academic.controller;

import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Controller
public class EnrollInCourseController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    // 강의 계획서 페이지
    @GetMapping("/enroll/course")
    public void get_course() {
    }

    // 수강 기간 설정
    // 설정 버튼을 누르면 시작 날짜와 종료 날짜 받아와서 서비스로 설정
    // GetMapping은 ManagerController에 구현함
    @PostMapping("/manager/set-period")
    public String post_set_enrolmentDate(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ){
        System.out.println("시작 날짜 : " + startDate + ", 종료 날짜 :" + endDate);
        enrollInCourseService.set_enrollDate(startDate, endDate);
        return "redirect:/manager/enrolmentStatus";
    }

    // 수강신청페이지 종료 버튼 누를 시
    // GetMapping은 ManagerController에 구현함
    @PostMapping("/manager/end-period")
    public String endPeriod() {
        return "redirect:/manager/enrolment";
    }
}
