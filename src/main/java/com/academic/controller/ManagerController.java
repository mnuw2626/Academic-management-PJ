package com.academic.controller;

import com.academic.dto.StdDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/add_std")
    public void get_add_std(){
    }

    @PostMapping("/add_std")
    public void post_add_std(StdDTO stdDTO){
        System.out.println("학생등록시도");
        managerService.manager_add_std(stdDTO);
        System.out.println("학생등록성공");
    }
}
