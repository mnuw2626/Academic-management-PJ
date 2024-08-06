package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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

    // 수강기간일 시 수강신청페이지로 이동
    @GetMapping("/course/enroll")
    public String get_enroll(
            @AuthenticationPrincipal UserDTO user,
            @RequestParam(value = "code", defaultValue = "-1") Integer code,
            Model model
    ){
        // 수강 신청 기간 비교
        LocalDate today = LocalDate.now();
        boolean result = enrollInCourseService.compare_enrollDate_now(today);
        StdDTO std = userService.select_user_info_service(user.getId());

        // 코드를 통해 교과목명을 조회
        String lectureName = null;
        if (code != -1) {
            lectureName = enrollInCourseService.get_lecture_name_by_code(code); // 강의 코드로 강의 이름 검색
            model.addAttribute("lectureName", lectureName);
        }

        if(result){
            // 수강된 과목 및 강의 과목들 조회
            Map<String, List<StdEnrollCourseDTO>> enroll = enrollInCourseService.get_std_course_details(std.getStdNo(), code, lectureName);
            System.out.println(enroll);
            model.addAttribute("enroll", enroll);

            // 현재 수강 중인 학점
            int totalCredits = enroll.get("stdEnrollCourse").stream()
                    .findFirst()
                    .map(StdEnrollCourseDTO::getStdCredit) // stdCredit을 가져옴
                    .orElse(0); // 값이 없을 경우 0 반환

            // 잔여 학점 계산
            int remainingCredits = StdEnrollCourseDTO.ALLCREDIT - totalCredits;
            model.addAttribute("totalCredits", totalCredits);
            model.addAttribute("remainingCredits", remainingCredits);

            return "course/enroll"; //신청기간이면
        }
        else
        {
            return "redirect:/main";//신청기간이 아니면 메인으로
        }
    }

    // 수강된 과목 시간표 조회 및 출력
    @GetMapping("/user/schedule")
    public String get_schedule(
            @AuthenticationPrincipal UserDTO user,
            Model model
    ){
        StdDTO std = userService.select_user_info_service(user.getId());

        if (std == null) {
            return "redirect:/user/login"; // 학생정보가 없음 로그인 페이지로
        }
        Map<String, List<StdEnrollCourseDTO>> courseDetails = enrollInCourseService.get_course_details(std.getStdNo(), -1, null);
        List<StdEnrollCourseDTO> stdEnrollCourses = courseDetails.get("stdEnrollCourse");


//        System.out.println(stdEnrollCourses);

        model.addAttribute("stdEnrollCourses", stdEnrollCourses);
        return "user/schedule"; // 다시 일정으로
    }


}
