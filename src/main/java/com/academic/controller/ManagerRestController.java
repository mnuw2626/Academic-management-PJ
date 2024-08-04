package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.CourseScoreService;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.academic.service.CourseScoreService.*;

@RestController
public class ManagerRestController {
    @Autowired
    ManagerService managerService;

    @Autowired
    CourseScoreService courseScoreService;

    @PostMapping("/manager/stuscore_regist")
    public Boolean post_stuscore_regist(
            @ModelAttribute GradeRequest gradeRequest
    ){
//        System.out.println(gradeRequest);
//        System.out.println(gradeRequest.getGrades());

        List<EnrollDTO> grades = gradeRequest.getGrades();

        for (EnrollDTO gradeForm : grades) {
            String grade = gradeForm.getGrade();
            int code = gradeForm.getCode();
            int stdNo = gradeForm.getStdNo();

            System.out.println("grade: " + grade + ", code: " + code + ", stdNo: " + stdNo);
            courseScoreService.update_std_enroll_coures(stdNo, code, grade);
        }
        
        return true;
    }

}
