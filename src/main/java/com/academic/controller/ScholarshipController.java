package com.academic.controller;

import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/scholarship")
public class ScholarshipController {
    @Autowired
    private UserService userService;

    @GetMapping("/bill")
    public String get_bill(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        boolean moneyOk = userService.std_status(userDTO.getNo());
        if (moneyOk)
        {
            model.addAttribute("message", "이미 제출했습니다.");
            return "scholarship/bill";
        }
        StdDTO stdDTO = userService.select_user_scholarship(userDTO.getNo());
        model.addAttribute("stdDTO", stdDTO);
        return "scholarship/bill";
    }

    @PostMapping("/bill")
    public String post_bill(@AuthenticationPrincipal UserDTO userDTO) {
        System.out.println("POST /bill called for user: " + userDTO.getNo());
        userService.update_scholarship(userDTO.getNo());
        StdDTO stdDTO = userService.select_user_scholarship(userDTO.getNo());
        return "redirect:/scholarship/bill";
    }
}
