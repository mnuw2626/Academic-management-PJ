package com.academic.controller;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/scholarship")
public class ScholarshipController {
    @Autowired
    private UserService userService;

    @Autowired
    private EnrollInCourseService enrollInCourseService;

    @GetMapping("/bill")
    public String get_bill(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        // 현재 날짜 기반으로 연도와 학기 계산
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int semester = (currentDate.getMonthValue() >= 7) ? 2 : 1;  // 7월 이상이면 2학기, 그 외에는 1학기

        model.addAttribute("currentYear", currentYear);
        model.addAttribute("semester", semester);

        int moneyOk = userService.std_status(userDTO.getNo());
        if(moneyOk == 0){
            model.addAttribute("message", moneyOk);
            return "scholarship/bill";
        }
        else if (moneyOk == 1)
        {
            model.addAttribute("message", moneyOk);
            return "scholarship/bill";
        }else {
            get_db_college_depart_info(model);
            model.addAttribute("message", moneyOk);
            StdDTO stdDTO = userService.select_user_scholarship(userDTO.getNo());
            model.addAttribute("stdDTO", stdDTO);
            return "scholarship/bill";
        }
    }

    @PostMapping("/bill")
    public String post_bill(@AuthenticationPrincipal UserDTO userDTO) {
        System.out.println("POST /bill called for user: " + userDTO.getNo());
        userService.update_scholarship(userDTO.getNo());
        return "redirect:/scholarship/bill";
    }

    /*  단과대학과 해당된 단과대학의 학과를 DB에서 조회하는 함수  */
    private void get_db_college_depart_info(Model model){
        // 페이지 접속 시 단과대학을 DB에서 조회
        List<CollegeDTO> colleges = enrollInCourseService.get_colleges();
        model.addAttribute("colleges", colleges);

        // 첫번째 단과대학의 학과를 조회
        List<DepartmentDTO> departments = enrollInCourseService.get_departments(colleges.get(0).getId());
        model.addAttribute("departments", departments);

        //모든 단과대학 조회
        List<DepartmentDTO> allDepartments = enrollInCourseService.get_all_departments();
        model.addAttribute("allDepartments", allDepartments);

        //단과대와 부서에 따른 맵생성
        Map<Integer, String> collegeMap = colleges.stream()
                .collect(Collectors.toMap(CollegeDTO::getId, CollegeDTO::getName));
        Map<Integer, String> allDepartmentMap = allDepartments.stream()
                .collect(Collectors.toMap(DepartmentDTO::getId, DepartmentDTO::getName));

        //맵을 모델에 추가
        model.addAttribute("collegeMap", collegeMap);
        model.addAttribute("allDepartmentMap", allDepartmentMap);
    }
}
