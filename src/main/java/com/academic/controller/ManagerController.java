package com.academic.controller;

import com.academic.dto.*;
import com.academic.dto.*;
import com.academic.service.CourseScoreService;
import com.academic.service.EnrollInCourseService;
import org.springframework.http.HttpStatus;
import com.academic.dto.TuitionDTO;
import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.NoticeDTO;
import org.springframework.ui.Model;
import com.academic.dto.StdDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private EnrollInCourseService enrollInCourseService;

    @Autowired
    private CourseScoreService courseScoreService;


    @GetMapping("/add_std")
    public void get_add_std(
            Model model
    ){
        get_db_college_depart_info(model);
    }

    @PostMapping("/add_std")
    public String post_add_std(StdDTO stdDTO){
        System.out.println("학생등록시도");
//        System.out.println("학생 정보 : " + stdDTO);
        managerService.manager_add_std(stdDTO);
        System.out.println("학생등록성공");
        return "redirect:/manager/add_std";
    }

    /**********학생명단조회**********/
    //학생 명단 조회 페이지 이동 및 조회
    @GetMapping("/student_list_check")
    public void get_student_list_check(
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) Integer collegeId,
            @RequestParam(required = false) Integer deptId,
            @RequestParam(required = false) Integer stdNo,
            @RequestParam(required = false) String name,
            Model model
    ){
        List<StdDTO> stdDTOS = managerService.manager_std_list_check(collegeId, deptId, grade, semester, name, stdNo);
        model.addAttribute("stds", stdDTOS);
        // 단과대학에 따른 학과 검색
        get_db_college_depart_info(model);
    }

    /**********학생 성적 등록**********/
    //학생 성적 등록 페이지로 이동
    @GetMapping("/stuscore_regist")
    public void get_stuscore_regist(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) Integer collegeId,
            @RequestParam(required = false) Integer deptId,
            @RequestParam(required = false) Integer stdNo,
            @RequestParam(required = false) String stdName,
            Model model
    ) {
        // 단과대학에 따른 학과 검색
        get_db_college_depart_info(model);

        // 등급 목록 조회
        List<GradeDTO> gradeList = courseScoreService.get_all_grade();
        model.addAttribute("gradeList", gradeList);

        // 수강 내역 조회 및 필터링
        List<CourseDetailsDTO> courseDetails = courseScoreService.get_all_grade_score();
        courseDetails = filterCourseDetails(courseDetails, year, semester, collegeId, deptId, stdNo, stdName);

        // 아직 성적이 등록되지 않은 항목만 필터링
        courseDetails = courseDetails.stream()
                .filter(course -> course.getEnroll().getGrade() == null)
                .collect(Collectors.toList());

        model.addAttribute("courseDetails", courseDetails);
    }

    private List<CourseDetailsDTO> filterCourseDetails(List<CourseDetailsDTO> courseDetails,
                                                       Integer year, Integer semester,
                                                       Integer collegeId, Integer deptId,
                                                       Integer stdNo, String stdName) {
        return courseDetails.stream()
                .filter(course -> year == null || course.getLecture().getGrade() == year)
                .filter(course -> semester == null || course.getLecture().getSemester() == semester)
                .filter(course -> collegeId == null || course.getCollege().getId() == collegeId)
                .filter(course -> deptId == null || course.getDepartment().getId() == deptId)
                .filter(course -> stdNo == null || course.getStd().getStdNo() == stdNo)
                .filter(course -> stdName == null || course.getStd().getName().contains(stdName))
                .collect(Collectors.toList());
    }


    @PostMapping("/stuscore_regist")
    public String post_stuscore_regist(@ModelAttribute GradeRequest gradeRequest) {
        List<EnrollDTO> grades = gradeRequest.getGrades();

        grades.forEach(grade -> {
            if (grade.getGrade() != null && !grade.getGrade().isEmpty()) {
                courseScoreService.set_std_enroll_coures_score(
                        grade.getStdNo(),
                        grade.getCode(),
                        grade.getGrade()
                );
            }
        });
        return "redirect:/manager/stuscore_regist";
    }


    /**************  수강 신청 기간 설정 페이지   *************/
    // 수강신청기간 설정 페이지
    @GetMapping("/enrolment")
    public void get_enrollment() {
        System.out.println("수강신청페이지");
    }

    // 수강 신청 기간일 때 페이지(수강신청기간 설정 페이지에서 설정 버튼을 누르면 아래 GetMapping 실행 )
    @GetMapping("/enrolmentStatus")
    public void enrolmentStatus(
            Model model
    ) {
        EnrollmentDateDTO peroid = enrollInCourseService.get_current_period();
        System.out.println(peroid);
        model.addAttribute("peroid", peroid);
        System.out.println("수강신청중");
    }

    /******************** 등록금 발송  *******************/
    //등록금 발송 페이지 이동
    @GetMapping("/send")
    public void send(){
        List<TuitionDTO> tuitionDTOS = managerService.get_tuitions();
        managerService.send_scholarship(tuitionDTOS);
    }

    /******************** 등록금 제출 내역 조회  *******************/
    @GetMapping("/bill_check")
    public void get_bill_check(
            Model model
    ){
        List<StdDTO> tuitionDTOS = managerService.get_all_std_tuitions();
        model.addAttribute("stdTuitions", tuitionDTOS);
    }

    /******************** 휴/복학 조회  *******************/
    // 휴/복학 신청 관리 페이지
    @GetMapping("/leave_management")
    public void get_leave_management(Model model) {
        List<LeaveDTO> leaveDTOS = managerService.get_all_leaves_info();
        List<LeaveDTO> returnDTOS = managerService.get_all_returns_info();
        model.addAttribute("leaves", leaveDTOS);
        model.addAttribute("returns", returnDTOS);
    }

    // 휴학 상태 업데이트
    @PostMapping("/leave/update")
    @ResponseBody
    public Map<String, Object> update_leave_status(
            @RequestParam("stdNo") Integer stdNo,
            @RequestParam("leaveCount") int leaveCount
    ) {
        boolean success = managerService.update_leave_status(stdNo, leaveCount);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return response;
    }

    // 복학 상태 업데이트
    @PostMapping("/return/update")
    @ResponseBody
    public Map<String, Object> update_return_status(
            @RequestParam("stdNo") Integer stdNo
    ) {
        boolean success = managerService.update_return_status(stdNo);
        if (success) {
            // 복학이 성공적으로 업데이트되면 해당 학생의 휴학 신청 정보를 삭제
            managerService.delete_leave_application(stdNo);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        return response;
    }

    /******************** 학사일정 *******************/
    // 학사일정
    @GetMapping("/manager_calendar")
    public void get_manager_calendar(){}

    // 공지사항 페이지 이동
    @GetMapping("/manager_notice")
    public void get_manager_notice(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String searchType,
            Model model
    ){
        List<NoticeDTO> notices = managerService.get_notices(title, searchType);
        model.addAttribute("notices", notices);
    }

    // 공지사항 추가 페이지
    @GetMapping("/notice_write")
    public void get_notice_write(){
    }

    // 공지사항 추가
    @PostMapping("/notice_write")
    public String post_notice_write(NoticeDTO noticeDTO){
        System.out.println(noticeDTO);
        managerService.insert_notice(noticeDTO);
        System.out.println(noticeDTO);
        return "redirect:/manager/manager_notice";
    }

    // 각각 공지사항으로 이동
    @GetMapping("/manager_view_notice/{noticeNo}")
    public String get_manager_view_notice(
            @PathVariable("noticeNo") Integer noticeNo,
            Model model
    ){
        NoticeDTO noticeDTO = managerService.get_notice(noticeNo);
        managerService.update_view(noticeNo);
        model.addAttribute("notice", noticeDTO);
        return "manager/manager_view_notice";  // 템플릿 파일의 경로 반환
    }

    // 각각 공지사항 수정
    @GetMapping("/notice_edit/{noticeNo}")
    public String get_notice_change(
            @PathVariable("noticeNo") Integer noticeNo,
            Model model
    ){
        NoticeDTO noticeDTO = managerService.get_notice(noticeNo);
        model.addAttribute("notice", noticeDTO);
        return "manager/notice_edit";  // 수정 페이지 템플릿 파일의 경로
    }

    @PostMapping("/notice_edit/{noticeNo}")
    public String post_notice_change(
            @PathVariable("noticeNo") Integer noticeNo,
            @ModelAttribute NoticeDTO noticeDTO
    ){
        noticeDTO.setNo(noticeNo); // noticeNo를 noticeDTO에 설정
        managerService.update_notice(noticeDTO);
        return "redirect:/manager/manager_view_notice/" + noticeNo;
    }

    // 공지사항 삭제
    @PostMapping("/notice_delete/{noticeNo}")
    public ResponseEntity<Void> delete_notice(
            @PathVariable("noticeNo") Integer noticeNo
    ){
        managerService.delete_notice(noticeNo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
