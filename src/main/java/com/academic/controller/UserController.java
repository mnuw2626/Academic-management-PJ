package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.EnrollInCourseService;
import com.academic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.stream.IntStream;

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
    public void get_register(){
//        return "user/student-number"; // 학생 번호 입력 페이지로 이동
    }

    @GetMapping("/student-number")
    public void get_student_number(){}

    // 학번 확인 후 회원가입 페이지로 이동
    @PostMapping("/register")
    public String post_register(@RequestParam int studentNo, Model model) {
        // 학번에 해당하는 학생 정보를 가져옴
        StdDTO student = userService.get_std_info(studentNo);
        if (student != null) {
            if(student.getId() == null) {
                model.addAttribute("studentNo", studentNo);
                model.addAttribute("studentName", student.getName());
                return "user/register"; // 회원가입 페이지로 이동
            }
            else {
                System.out.println("이미 가입되어있는 학생임");
                return "redirect:/user/login"; // 이미 가입되어있으면 로그인 페이지로
            }
        }
        System.out.println("학생 정보 없음");
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
        model.addAttribute("userInfo", userInfo);

        get_db_college_depart_info(model);
        
        return "user/info_management";
    }

    @GetMapping("/academic_calendar")
    public void get_academic_calendar(){}

    //공지사항 페이지로 이동
    @GetMapping("/academic_notice")
    public void get_academic_notice(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String searchType,
            Model model
    ){
        List<NoticeDTO> notices = userService.get_notices(title, searchType);
        model.addAttribute("notices", notices);
    }

    //각각 공지사항들
    @GetMapping("/view_notice/{noticeNo}")
    public String get_view_notice(
            @PathVariable Integer noticeNo,
            Model model
    ){
        NoticeDTO notice = userService.get_notice(noticeNo);
        userService.update_view(noticeNo);
        model.addAttribute("notice", notice);
        return "user/view_notice";
    }

    //성적조회
    @GetMapping("/score")
    public String get_score(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester,
            @AuthenticationPrincipal UserDTO userDTO,
            Model model
    ){
        StdDTO userInfo = userService.select_user_info_service(userDTO.getId());

        // 현재 연도
        int currentYear = Year.now().getValue();

        List<Integer> availableYears = IntStream.range(0, userInfo.getGrade())
                                       .mapToObj(i -> currentYear - i)
                                       .collect(Collectors.toList());

        model.addAttribute("availableYears", availableYears);

        // 성적 조회

        List<CourseDetailsDTO> courseDetails = userService.get_score(userDTO.getNo(), year, semester);
        System.out.println(courseDetails);
        model.addAttribute("courseDetails", courseDetails);


        return "user/score";
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
        Map<Integer, String> allcollegeMap = colleges.stream()
                .collect(Collectors.toMap(CollegeDTO::getId, CollegeDTO::getName));
        Map<Integer, String> allDepartmentMap = allDepartments.stream()
                .collect(Collectors.toMap(DepartmentDTO::getId, DepartmentDTO::getName));

        // Add maps to the model
        model.addAttribute("allcollegeMap", allcollegeMap);
        model.addAttribute("allDepartmentMap", allDepartmentMap);
    }
}
