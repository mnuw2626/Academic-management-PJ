package com.academic.controller;


import com.academic.dto.*;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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


    // 수강 신청 버튼 클릭 시
    @PostMapping("/enroll/regist/{code}")
    public ResponseEntity<String> post_course_details(
            @AuthenticationPrincipal UserDTO user,
            @PathVariable("code") Integer code
    ){
        System.out.println(code);
        StdDTO std = userService.select_user_info_service(user.getId());
        System.out.println(std);
        if (std == null) {
            // 오류 처리: 학생 정보가 없음
            return ResponseEntity.badRequest().body("학생 정보가 없습니다.");
        }

        // 수강신청 버튼 누를 시 수강정보 DB 삽입
        String result = enrollInCourseService.set_course_details(std.getStdNo(), code);
        switch (result){
            case "SUCCESS":
                System.out.println("수강 신청 내역 등록 성공");
                enrollInCourseService.num_of_student(code);
                return ResponseEntity.ok("수강 신청 성공");
            case "FULL":
                System.out.println("강의 인원 초과");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("강의 인원 초과");
            case "DUPLICATE":
                System.out.println("수강 시간 중복");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("수강 시간 중복");
            case "CREDIT":
                System.out.println("신청 가능한 학점 초과");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("신청 가능한 학점 초과");
            default:
                System.out.println("오류로 수강 실패");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("오류로 수강 실패");
        }
    }

    // 과목코드 입력 시 과목명이 자동으로 뜨게 하기위해 사용
    @GetMapping("/course/getLectureName")
    public String get_code_name_lecture(
            @RequestParam(required = false) Integer code
    ) {
        return enrollInCourseService.get_lecture_name_by_code(code); //강의 이름만 반환함
    }

    // 과목코드로 강의 조회 및 전체 강의 조회
    @GetMapping("/course/lectures")
    public List<LectureDTO> get_code_lecture(
            @RequestParam(required = false) Integer code
    ) {
        if (code != null) { //코드 입력 시
            return enrollInCourseService.get_code_lecture(code);
        } else { //코드 미 입력시
            return enrollInCourseService.get_all_lectures();
        }
    }

}
