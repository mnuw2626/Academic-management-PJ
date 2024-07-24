package com.academic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("add_std")
    public void get_add_std(){
        System.out.println("학생등록시도");

    }
    @GetMapping("/stuscore_regist")
    public void get_stuscore_regist(){

    }
}
