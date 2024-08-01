package com.academic.controller;

import com.academic.dto.LectureDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    @GetMapping("/main")
    public void get_main() {}

    @GetMapping("/manager_main")
    public void get_manager_main(){}


    // 시간표 페이지로 이동
    @GetMapping("/user/schedule")
    public void get_user_schedule(){}


}
