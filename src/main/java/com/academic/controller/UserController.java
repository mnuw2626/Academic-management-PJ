package com.academic.controller;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.service.EnrollInCourseService;
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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EnrollInCourseService enrollInCourseService;

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
        get_db_college_depart_info(model);
        StdDTO userInfo = userService.select_user_info_service(userDTO.getId());
        if (userInfo != null) {
            System.out.println("User Info: " + userInfo);
            model.addAttribute("userInfo", userInfo);
        } else {
            System.out.println("User Info is null");
        }
        return "user/info_management";
    }

    /*  단과대학과 해당된 단과대학의 학과를 DB에서 조회하는 함수  */
    private void get_db_college_depart_info(Model model){
        // 페이지 접속 시 단과대학을 DB에서 조회
        List<CollegeDTO> colleges = enrollInCourseService.get_colleges();
        model.addAttribute("colleges", colleges);

        // 처음으로 페이지 들어갔을 시 첫번째 단과대학의 학과를 조회
        // -> 그 후 단과대학 선택 시 그에 따른 학과 조회는 ManagerRestController 참고
        Integer college1_id = colleges.get(0).getId();
        List<DepartmentDTO> departments = enrollInCourseService.get_departments(college1_id);
        model.addAttribute("departments", departments);

        //모든 단과대학 조회
        List<DepartmentDTO> allDepartments = enrollInCourseService.get_all_departments();
        model.addAttribute("allDepartments", allDepartments);

        // Create maps for college and department names
        Map<Integer, String> collegeMap = colleges.stream()
                .collect(Collectors.toMap(CollegeDTO::getId, CollegeDTO::getName));
        Map<Integer, String> allDepartmentMap = allDepartments.stream()
                .collect(Collectors.toMap(DepartmentDTO::getId, DepartmentDTO::getName));

        // Add maps to the model
        model.addAttribute("collegeMap", collegeMap);
        model.addAttribute("allDepartmentMap", allDepartmentMap);
    }


}
