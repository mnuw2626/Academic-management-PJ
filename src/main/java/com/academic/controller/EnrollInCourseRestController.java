package com.academic.controller;


import com.academic.dto.LectureDTO;
import com.academic.service.EnrollInCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class EnrollInCourseRestController {
    @Autowired
    EnrollInCourseService enrollInCourseService;

    // 전체 강의 조회
    @GetMapping("/course/lectures")
    public List<LectureDTO> get_lectures() {
        return enrollInCourseService.get_all_lectures();
    }

    // 서비스에 수강신청 기간 설정
    @PostMapping("/check/enrolment")
    public ResponseEntity<Boolean> post_is_enrolmentDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        boolean result = enrollInCourseService.compare_enrollDate_now(startDate, endDate);

        // Return result
        return ResponseEntity.ok().body(result);
    }
}
