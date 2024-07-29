package com.academic.controller;

import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    @GetMapping("/main")
    public void get_main() {

    }

    @GetMapping("/manager_main")
    public void get_manager_main(){}

    // 시간표 페이지로 이동
    @GetMapping("/user/schedule")
    public void get_user_schedule(){}

    //전체 수업 조회
    @GetMapping("/course/course")
    public void get_course(){}

    @GetMapping("/course/enroll")
    public void get_enroll(
    ){
//        enrollInCourseService.compare_enrollDate();
    }

}
