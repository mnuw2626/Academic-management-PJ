package com.academic.controller;

import com.academic.dto.StdDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    /**********학생등록**********/

    @GetMapping("/add_std")
    public void get_add_std(){
    }

    @PostMapping("/add_std")
    public String post_add_std(StdDTO stdDTO){
        System.out.println("학생등록시도");
        managerService.manager_add_std(stdDTO);
        System.out.println("학생등록성공");
        return "redirect:/manager/add_std";
    }

    /**********학생명단조회**********/

    @GetMapping("/student_list_check")
    public void get_student_list_check(){

    }

    @GetMapping("/student_list")
    public ResponseEntity<List<StdDTO>> get_student_list(
        @AuthenticationPrincipal StdDTO stdDTO
    ) {
        System.out.println("학생명단조회시작");
        List<StdDTO> stdDTOS = managerService.manager_std_list_check(stdDTO);
        return ResponseEntity.ok(stdDTOS);
    }

    /**********성적등록**********/

    @GetMapping("/stuscore_regist")
    public void get_stuscore_regist(){
    }

    @GetMapping("/enrolment")
    public void get_enrolment(){

    }

}
