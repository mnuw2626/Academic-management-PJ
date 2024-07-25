package com.academic.controller;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import org.springframework.ui.Model;
import com.academic.dto.StdDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;


    @GetMapping("/add_std")
    public void get_add_std(
            Model model
    ){
        get_db_college_depart_info(model);
    }

    @PostMapping("/add_std")
    public String post_add_std(StdDTO stdDTO){
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
            Model model
    ){
        get_db_college_depart_info(model);
    }

    // 학생 명단 조회 기능
    @GetMapping("/student_list")
    public ResponseEntity<List<StdDTO>> get_student_list(
            @AuthenticationPrincipal StdDTO stdDTO
    ) {
        System.out.println("학생명단조회시작");
        List<StdDTO> stdDTOS = managerService.manager_std_list_check(stdDTO);
        return ResponseEntity.ok(stdDTOS);
    }


   /******************** 성적 등록  *******************/
    // 성적 등록 페이지 이동
    @GetMapping("/stuscore_regist")
    public void get_stuscore_regist(
                Model model
    ){
        get_db_college_depart_info(model);
    }

    // 수강 기간 설정
    @GetMapping("/enrolment")
    public void get_enrolment(){

    }

    /*  단과대학과 해당된 단과대학의 학과를 DB에서 조회하는 함수  */
    private void get_db_college_depart_info(Model model){
        // 페이지 접속 시 단과대학을 DB에서 조회
        List<CollegeDTO> colleges = managerService.get_colleges();
        model.addAttribute("colleges", colleges);

        // 처음으로 페이지 들어갔을 시 첫번째 단과대학의 학과를 조회
        // -> 그 후 단과대학 선택 시 그에 따른 학과 조회는 ManagerRestController 참고
        Integer college1_id = colleges.get(0).getId();
        List<DepartmentDTO> departments = managerService.get_departments(college1_id);
        model.addAttribute("departments", departments);
    }
}
