package com.academic.controller;

import com.academic.dto.NoticeDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.util.List;

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


    @GetMapping("/academic_calendar")
    public void get_academic_calendar(){}

    @GetMapping("/academic_notice")
    public void get_academic_notice(Model model){
        List<NoticeDTO> notices = userService.get_notices();
        model.addAttribute("notices", notices);
    }

    @GetMapping("/view_notice/{noticeNo}")
    public String get_view_notice(
            @PathVariable String noticeNo,
            Model model
    ){
        NoticeDTO notice = userService.get_notice(noticeNo);
        model.addAttribute("notice", notice);
        return "user/view_notice";
    }
}
