package com.academic.controller;

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
@RequestMapping("/scholarship")
public class ScholarshipController {
    @Autowired
    private UserService userService;

    @GetMapping("/bill")
    public void get_bill(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        StdDTO stdDTO = userService.select_user_scholarship(userDTO.getNo());
        model.addAttribute("stdDTO", stdDTO);
    }

    @PostMapping("/bill")
    public String post_bill(
            @AuthenticationPrincipal UserDTO userDTO
    ){
        userService.update_scholarship(userDTO.getNo());
        return "redirect:/scholarship/bill";
    }
}
