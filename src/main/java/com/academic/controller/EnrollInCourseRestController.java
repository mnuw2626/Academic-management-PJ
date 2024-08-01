package com.academic.controller;


import com.academic.dto.*;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
public class EnrollInCourseRestController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    @Autowired
    UserService userService;

    //수강신청기간 비교(현재 날짜가 설정된 시작, 종료 날짜 사이이면 true, 아니면 false 반환 )
    @GetMapping("/course/is/enroll")
    public Boolean get_enroll(){
        LocalDate today = LocalDate.now();
        return enrollInCourseService.compare_enrollDate_now(today);
    }

    // 단과대학 조회
    @GetMapping("/colleges")
    public List<CollegeDTO> get_find_college(){
        return enrollInCourseService.get_colleges();
    }

    // 단과대학에 해당하는 학과 정보 조회
    @GetMapping("/college/{collegeId}/depart")
    public List<DepartmentDTO> get_find_department(
            @PathVariable Integer collegeId
    ){
        return enrollInCourseService.get_departments(collegeId);
    }

    // 전체 강의 조회
    @GetMapping("/course/lectures")
    public List<LectureDTO> get_code_lecture(
            @RequestParam(required = false) Integer code
    ) {
        if (code != null) { //코드 입력 시
            return enrollInCourseService.get_code_lecture(code);
        }
        else { //코드 미 입력시
            return enrollInCourseService.get_all_lectures();
        }
    }

    @PostMapping("/enroll/regist/{code}")
    public void post_course_details(
            @AuthenticationPrincipal UserDTO userDTO,
            @PathVariable("code") Integer code
    ){
        System.out.println(code);
        // 로그인된 사용자의 학생 정보를 조회
        StdDTO student = userService.select_user_info_service(userDTO.getId());
//        System.out.println(student);
        if (student == null) {
            // 오류 처리: 학생 정보가 없음
            return;
        }
        // 로그인된 학생의 학번을 가져옴
        int stdNo = student.getStdNo();
        boolean result = enrollInCourseService.set_course_details(stdNo, code);
        if(!result){
            System.out.println("등록실패");
        }
        System.out.println("수강 신청 내역 등록 성공");
    }

}
