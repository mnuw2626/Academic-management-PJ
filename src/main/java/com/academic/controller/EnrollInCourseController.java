package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
public class EnrollInCourseController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    @Autowired
    UserService userService;

    // 강의 계획서 페이지
    @GetMapping("/course/course")
    public void get_course(
            @AuthenticationPrincipal UserDTO userDTO,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) String name,
            Model model
    ) {
        // 로그인된 사용자의 학생 정보를 조회
        StdDTO student = userService.select_user_info_service(userDTO.getId());
        System.out.println(student);
        if (student == null) {
            // 오류 처리: 학생 정보가 없음
            return;
        }

        // 로그인된 학생의 학과 id를 가져옴
        int deptId = student.getDeptId();

        //가져온 학과 id로 학과 정보를 조회
        DepartmentDTO department = enrollInCourseService.get_department(deptId);
        model.addAttribute("depart", department);

        System.out.println("강의 조희");
        List<LectureDTO> lectures = enrollInCourseService.get_all_lecture(type, grade, name);
        model.addAttribute("lectures", lectures);
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
        enrollInCourseService.set_enrollDate(null, null);//설정된 시작,종료 날짜 초기화
        return "redirect:/manager/enrolment";
    }
}
