package com.academic.controller;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.EnrollmentDateDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.ui.Model;
import com.academic.dto.StdDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    EnrollInCourseService enrollInCourseService;

    @GetMapping("/add_std")
    public void get_add_std(
            Model model
    ) {
        get_db_college_depart_info(model);
    }

    @PostMapping("/add_std")
    public String post_add_std(StdDTO stdDTO) {
        System.out.println("학생등록시도");
//        System.out.println("학생 정보 : " + stdDTO);
        managerService.manager_add_std(stdDTO);
        System.out.println("학생등록성공");
        return "redirect:/manager/add_std";
    }

    /**********학생명단조회**********/
    //학생 명단 조회 페이지 이동
    @GetMapping("/student_list_check")
    public void student_list_check(
            @RequestParam(required = false) Integer collegeId,
            @RequestParam(required = false) Integer deptId,
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String stdNo,
            Model model
    ) {
        System.out.println("학생명단조회시작");
        List<StdDTO> stds = managerService.manager_std_list_check(collegeId, deptId, grade, semester, name, stdNo);
        model.addAttribute("stds", stds);
        //단과대 조회
        get_db_college_depart_info(model);
    }


    /******************** 성적 등록  *******************/
    // 성적 등록 페이지 이동
    @GetMapping("/stuscore_regist")
    public void get_stuscore_regist(
            Model model
    ) {
        get_db_college_depart_info(model);
    }


    /*  단과대학과 해당된 단과대학의 학과를 DB에서 조회하는 함수  */
    private void get_db_college_depart_info(Model model) {
        // 페이지 접속 시 단과대학을 DB에서 조회
        List<CollegeDTO> colleges = managerService.get_colleges();
        model.addAttribute("colleges", colleges);

        // 처음으로 페이지 들어갔을 시 첫번째 단과대학의 학과를 조회
        // -> 그 후 단과대학 선택 시 그에 따른 학과 조회는 ManagerRestController 참고
        Integer college1_id = colleges.get(0).getId();
        List<DepartmentDTO> departments = managerService.get_departments(college1_id);
        model.addAttribute("departments", departments);
    }

    // 수강신청기간 설정 페이지
    @GetMapping("/enrolment")
    public void get_enrollment() {
        enrollInCourseService.set_enrollDate(null, null);//예전에 설정된 시작,종료 날짜 초기화
        System.out.println("수강신청페이지");
    }

    // 수강 신청 기간일 때 페이지(수강신청기간 설정 페이지에서 설정 버튼을 누르면 아래 GetMapping 실행 )
    @GetMapping("/enrolmentStatus")
    public void enrolmentStatus(
            Model model
    ) {
        EnrollmentDateDTO peroid = enrollInCourseService.get_current_period();
        System.out.println(peroid);
        model.addAttribute("peroid", peroid);
        System.out.println("수강신청중");
    }

}