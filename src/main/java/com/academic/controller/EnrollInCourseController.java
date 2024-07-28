package com.academic.controller;


import com.academic.dto.LectureDTO;
import org.springframework.ui.Model;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/enroll")
public class EnrollInCourseController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    // 강의 계획서 페이지
    @GetMapping("/course")
    public void get_course() {

    }
}
