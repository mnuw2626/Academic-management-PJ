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

    // 수강기간일 시 수강신청페이지로 이동
    @GetMapping("/course/enroll")
    public String get_enroll(
            @AuthenticationPrincipal StdDTO std,
            @RequestParam(required = false) Integer code,
            @RequestParam(required = false) String lectureName
    ){
        // 수강 신청 기간 비교
        LocalDate today = LocalDate.now();
        boolean result = enrollInCourseService.compare_enrollDate_now(today);
        if(result){
            List<StdEnrollCourseDTO> list = enrollInCourseService.get_std_course_details(std.getStdNo());
            return "course/enroll"; //신청기간이면
        }
        else
        {
            return "redirect:/main";//신청기간이 아니면 메인으로
        }
    }

//    // 과목코드로 강의 조회 및 전체 강의 조회
//    @GetMapping("/course/lectures")
//    public String get_code_lecture(
//            @RequestParam(required = false) Integer code,
//            Model model
//    ) {
//        //수강신청내역 조회 후 model 해야할 듯(학번과 코드로 수강 신청 내역에 있으면 제외하고 출력)
//
//
//        if (code != null) { //코드 입력 시
//            List<LectureDTO> lectures = enrollInCourseService.get_code_lecture(code);
//            model.addAttribute("lectures", lectures);
//        }
//        else { //코드 미 입력시
//            List<LectureDTO> lectures = enrollInCourseService.get_all_lectures();
//            model.addAttribute("lectures", lectures);
//        }
//        return "course/enroll";
//    }
//
//    // 과목코드로 과목명 조회하기 위함
//    @GetMapping("/course/lecture")
//    public ResponseEntity<List<StdEnrollCourseDTO>> get_code_name_lecture(
//            @AuthenticationPrincipal StdDTO student,
//            @RequestParam(required = false) Integer code,
//
//            ) {
//        List<StdEnrollCourseDTO> lectures = enrollInCourseService.get_std_course_details(student.getStdNo());
//    }
//
//    // 수강 신청된 과목들 조회(학번과 이름으로 검색)
//    @GetMapping("/course/enrollStd")
//    public String get_enroll_in_course(
//            @AuthenticationPrincipal UserDTO userDTO,
//            @RequestParam(required = false) String listname,
//            Model model
//    ){
//        // 로그인된 사용자의 학생 정보를 조회
//        StdDTO student = userService.select_user_info_service(userDTO.getId());
//
//        // 로그인된 학생의 학번을 가져옴
//        int stdNo = student.getStdNo();
//        List<StdEnrollCourseDTO> list = enrollInCourseService.get_std_course_details(stdNo);
//        model.addAttribute("list", list);
//
//        return "course/enroll";
//    }

}
