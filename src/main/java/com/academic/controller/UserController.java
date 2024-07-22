package com.academic.controller;

import com.academic.dto.UserDTO;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired private UserService userService;

    //로그인 창으로 이동
    @GetMapping("/login")
    public void get_login() {
    }
    @PostMapping("/login")
    public void post_login() {
    }

    // 회원가입 창으로 이동
    @GetMapping("/register")
    public void get_register(){}

    // 회원가입 시도
    @PostMapping("/register")
    public String post_register(UserDTO userDTO){
        System.out.println("post_user_register - 회원가입시도");
        userService.user_register(userDTO);
        return "redirect:/user/login";
    }

//    로그아웃 기능을 a테크로 getmapping으로 구현
    @GetMapping("/logout")
    public String get_logout(){
        System.out.println("로그아웃 시도");
        return "redirect:/user/login";
    }
}
