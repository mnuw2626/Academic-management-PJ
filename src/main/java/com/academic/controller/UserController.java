package com.academic.controller;

import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public String get_register(){
        return "user/student-number"; // 학생 번호 입력 페이지로 이동
    }

    // 학번 확인 후 회원가입 페이지로 이동
    @PostMapping("/register")
    public String post_register(@RequestParam int studentNo, Model model) {
        // 학번에 해당하는 학생 정보를 가져옴
        StdDTO student = userService.get_std_info(studentNo);
        if (student != null) {
            model.addAttribute("studentNo", studentNo);
            model.addAttribute("studentName", student.getName());
            return "user/register"; // 회원가입 페이지로 이동
        }
        // 학생 정보가 존재하지 않는 경우 에러 처리
        return "redirect:/user/login"; // 로그인페이지로 이동시킴
    }

    // 회원가입 시도
    @PostMapping("/setregister")
    public String post_register(
            UserDTO userDTO,
            @RequestParam Integer studentNo
    ){
        System.out.println("post_user_register - 회원가입시도");
        userDTO.setNo(studentNo);

        // 권한을 설정 ("STUDENT")<-학생 권한 설정
        userDTO.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("STUDENT")));
        System.out.println("유저 : "+ userDTO + "학번 : " + studentNo);
//        System.out.println(userDTO.getNo());
        userService.user_register(userDTO);
        userService.set_std_id(userDTO.getId(), userDTO.getNo());
        return "redirect:/user/login";
    }

    /***************학생 정보*******************/

    @GetMapping("/info_management")
    public String get_info(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ) {
        StdDTO userInfo = userService.select_user_info_service(userDTO.getId());
        if (userInfo != null) {
            System.out.println("User Info: " + userInfo);
            model.addAttribute("userInfo", userInfo);
        } else {
            System.out.println("User Info is null");
        }
        return "user/info_management";
    }


}
