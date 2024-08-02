package com.academic.controller;

import com.academic.dto.LeaveDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private UserService userService;

    @GetMapping("/leave")
    public void get_leave(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        StdDTO stdDTO = userService.select_user_info_service(userDTO.getId());
        model.addAttribute("std", stdDTO);
        LeaveDTO leaveDTO = userService.select_user_stat(userDTO.getNo());
        model.addAttribute("leaveInfo", leaveDTO);
    }

    @PostMapping("/leave")
    public String post_leave(
            @AuthenticationPrincipal UserDTO userDTO,
            LeaveDTO leaveDTO
    ){
        LeaveDTO leave_std = userService.select_user_stat(userDTO.getNo());
        userService.insert_leave_std(leaveDTO);
        return "redirect:/school/leave";
    }
}
