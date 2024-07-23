package com.academic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class NoticeManagerController {

    @GetMapping("/notice")
    public String get_notice(){
        return "manager/manager_notice";
    }

    @GetMapping("/notice_write")
    public void notice_write(){}

}
