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

import java.time.LocalDate;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private UserService userService;

    @GetMapping("/leave")
    public String get_leave(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        StdDTO stdDTO = userService.select_user_info_service(userDTO.getId());
        model.addAttribute("std", stdDTO);
        LeaveDTO leaveDTO = userService.select_user_stat(userDTO.getNo());
        model.addAttribute("leaveInfo", leaveDTO);

        // 현재 날짜를 확인하여 신청 가능한 기간인지 체크
        LocalDate currentDate = LocalDate.now();
        boolean canApplyLeave = userService.isLeaveApplicationPeriod(currentDate);
        model.addAttribute("canApplyLeave", canApplyLeave);

        return "school/leave";
    }

    @PostMapping("/leave")
    public String post_leave(
            @AuthenticationPrincipal UserDTO userDTO,
            LeaveDTO leaveDTO
    ){
        LeaveDTO std = userService.select_user_stat(userDTO.getNo());
        leaveDTO.setLeaveCount(std.getLeaveCount());
        userService.insertLeaveApplication(leaveDTO);
        return "redirect:/school/leave";
    }

    @GetMapping("/return")
    public String get_return(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        StdDTO stdDTO = userService.select_user_info_service(userDTO.getId());
        model.addAttribute("std", stdDTO);
        LeaveDTO returnDTO = userService.select_user_stat(userDTO.getNo());
        System.out.println("Return Info: " + returnDTO);
        model.addAttribute("returnInfo", returnDTO);

        // 현재 날짜를 확인하여 신청 가능한 기간인지 체크
        LocalDate currentDate = LocalDate.now();
        boolean canApplyReturn = userService.isReturnApplicationPeriod(currentDate);
        model.addAttribute("canApplyReturn", canApplyReturn);

        return "school/return";
    }

    @PostMapping("/return")
    public String post_return(
            LeaveDTO leaveDTO
    ){
        userService.insertReturnApplication(leaveDTO);
        return "redirect:/school/return";
    }
}
