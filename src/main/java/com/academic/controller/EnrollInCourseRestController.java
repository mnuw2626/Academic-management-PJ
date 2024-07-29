package com.academic.controller;


import com.academic.dto.LectureDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            Model model
    ){

    }

}
