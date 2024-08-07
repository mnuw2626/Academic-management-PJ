package com.academic.controller;

import com.academic.dto.*;
import com.academic.service.CourseScoreService;
import com.academic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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



    @PostMapping("/leave/check")
    public ResponseEntity<Map<String, Object>> update_leave_status(@RequestParam("stdNo") Integer stdNo) {
        System.out.println(stdNo);
        // LeaveDTO 객체를 업데이트하는 메서드 호출
        boolean success = managerService.update_leave_status(stdNo);

        // 성공 여부를 포함한 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        // HTTP 200 OK 응답 반환
        return ResponseEntity.ok(response);
    }

}
