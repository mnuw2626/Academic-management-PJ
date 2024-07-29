package com.academic.controller;


import com.academic.dto.LectureDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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

    // 수강신청 버튼 누를시
    @PostMapping("/manager/enrolment")
    public void post_enrolment(
            @RequestParam("start-date") String starDate,
            @RequestParam("end-date") String endDate,
            RedirectAttributes redirectAttributes
    ){
//        System.out.println(starDate + "~" + endDate);
    }

}
