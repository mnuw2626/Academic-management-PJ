package com.academic.controller;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManagerRestController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/colleges")
    public List<CollegeDTO> get_find_college(){
        return managerService.get_colleges();
    }

    // 단과대학에 해당하는 학과 정보 조회
    @GetMapping("/college/{collegeId}/depart")
    public List<DepartmentDTO> get_find_department(
            @PathVariable Integer collegeId
    ){
        return managerService.get_departments(collegeId);
    }
}
