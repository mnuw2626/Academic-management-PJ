package com.academic.controller;


import com.academic.dto.LectureDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/course")
public class EnrollInCourseRestController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    // 전체 강의 조회
    @GetMapping("/lectures")
    public List<LectureDTO> get_lectures() {
        return enrollInCourseService.get_all_lectures();
    }
}