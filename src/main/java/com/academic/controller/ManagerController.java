package com.academic.controller;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

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

//    성적 등록
    @GetMapping("/stuscore_regist")
    public void get_stuscore_regist(
            Model model
    ){
        List<CollegeDTO> colleges = managerService.get_colleges();
        model.addAttribute("colleges", colleges);
    }

//    수강 기간 설정
    @GetMapping("/enrolment")
    public void get_enrolment(){
    }

}
