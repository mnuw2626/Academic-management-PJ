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

    // 단과대학에 해당하는 학과 정보 조회
    @GetMapping("/college/{collegeId}/depart")
    public List<DepartmentDTO> get_find_department(
            @PathVariable Integer collegeId
    ){
        return managerService.get_departments(collegeId);
    }



}
