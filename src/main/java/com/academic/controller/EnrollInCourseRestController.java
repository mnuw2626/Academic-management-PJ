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
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr
    ){
        // Parse date 데이터 LocalDate로 변환
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        System.out.println("시작 날짜 : " + startDate);
        System.out.println("종료 날짜 : " + endDate);

        // 서비스에서 현재날짜와 비교, 현재날짜가 시작과 종료 날짜 사이에 있으면 true
        boolean result = enrollInCourseService.compare_enrollDate_now(startDate, endDate);

        System.out.println(result);

        // Return result
        return ResponseEntity.ok().body(result);
    }
}
