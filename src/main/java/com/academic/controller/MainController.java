package com.academic.controller;

import com.academic.dto.LectureDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    @Autowired
    UserService userService;

    @GetMapping("/main")
    public void get_main(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ) {
        // 로그인된 사용자의 학생 정보를 조회
        StdDTO student = userService.select_user_info_service(userDTO.getId());
        System.out.println(student);
        model.addAttribute("student", student);
    }

    @GetMapping("/manager_main")
    public void get_manager_main(){}

}
