package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.EnrollInCourseService;
import com.academic.service.ManagerService;
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
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    @Autowired
    UserService userService;

    @Autowired
    ManagerService managerService;


    @GetMapping("/main")
    public void get_main(
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ) {
        // 로그인된 사용자의 학생 정보를 조회
        StdDTO student = userService.select_user_info_service(userDTO.getId());
        System.out.println(student);
        model.addAttribute("student", student);

        // 해당 학생의 단과대학정보와 학과정보
        get_college_depart_name(student, model);

    }

    @GetMapping("/manager_main")
    public void get_manager_main() {}

    public void get_college_depart_name(StdDTO std, Model model){
        List<CollegeDTO> colleges = managerService.get_colleges();


        // 단과대학 ID가 desiredCollegeId와 일치하는 단과대학을 찾음
        CollegeDTO college = null;
        for (CollegeDTO findCollege : colleges) {
            if (findCollege.getId() == std.getCollegeId()) {
                college = findCollege;
                break;
            }
        }

        //가져온 학과 id로 학과 정보를 조회
        DepartmentDTO department = enrollInCourseService.get_department(std.getDeptId());

        // 찾은 단과대학과 학과의 정보를 뷰로 넘김
        model.addAttribute("college", college);
        model.addAttribute("department", department);
    }

}
