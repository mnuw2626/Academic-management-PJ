package com.academic.service;


import com.academic.dto.*;
import com.academic.mapper.EnrollInCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnrollInCourseService {

    @Autowired
    EnrollInCourseMapper enrollInCourseMapper;

    // 날짜를 담을 객체 생성
    private EnrollmentDateDTO currentPeriod = new EnrollmentDateDTO(LocalDate.now(), LocalDate.now());

    // 날짜 설정하는 기능
    public void set_enrollDate(LocalDate startDate, LocalDate endDate) {
        currentPeriod.setStartDate(startDate);
        currentPeriod.setEndDate(endDate);
    }

    //현재 날짜와 저장된(관리자-수강신청기간설정페이지에서 설정) 날짜와 비교하여
    // 현재 날짜가 시작 날짜와 종료 날짜 사이에 있으면 true 로 반환
    public Boolean compare_enrollDate_now(LocalDate date){
        LocalDate startDate = currentPeriod.getStartDate();
        LocalDate endDate = currentPeriod.getEndDate();
        return startDate != null && endDate != null && !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    // 날짜 가져오는 함수(생성된 객체 반환)
    public EnrollmentDateDTO get_current_period() {
        return currentPeriod;
    }


    // 전체 강의 조회
    public List<LectureDTO> get_all_lectures() {
        return enrollInCourseMapper.select_all_lectures();
    }

    // 과목명, 학년, 타입(전공/교양)으로 강의 조회
    public List<LectureDTO> get_all_lecture(String type, Integer grade, String name)
    {
        return enrollInCourseMapper.select_all_lecture(type, grade, name);
    }

    // 과목 코드로 강의 검색
    public List<LectureDTO> get_code_lecture(Integer code) {
        return enrollInCourseMapper.select_code_lecture(code);
    }

    //    단과대학 정보 조회 - 단과대학 DB에서 가져옴
    public List<CollegeDTO> get_colleges() {
        return enrollInCourseMapper.select_colleges();
    }

    //    단과대학에 따른 학과 조회
    public List<DepartmentDTO> get_departments(Integer collegeId) {
        return enrollInCourseMapper.select_dept(collegeId);
    }

    // 학과 id로 학과 조회
    public DepartmentDTO get_department(Integer deptId) {
        return enrollInCourseMapper.select_department(deptId);
    }

    // 수강한 과목 조회하거나 전체 강의 조회
    public Map<String, List<StdEnrollCourseDTO>> get_std_course_details(Integer stdNo, Integer code, String lectureName){
        return get_course_details(stdNo, code, lectureName);
    }

    //강의 인원 수 +1
    public void num_of_student(Integer code){
        enrollInCourseMapper.update_lecture_num_of_student(code);
    };

    // 강의코드로 강의 이름 조회
    public String get_lecture_name_by_code(Integer code) {
        return enrollInCourseMapper.select_lecture_name_by_code(code);
    }

    //학번과 과목코드를 수강내역테이블(course_details)에 삽입
    public String set_course_details(Integer stdNo, Integer code) {

        Map<String, List<StdEnrollCourseDTO>> courseDetails = get_course_details(stdNo, code, null);
        List<StdEnrollCourseDTO> currentCourses = courseDetails.get("stdEnrollCourse");
        List<StdEnrollCourseDTO> enrollCourseDTOS = courseDetails.get("allEnrollCourse");

        // 새로 등록하려는 강의 정보 조회
        Optional<StdEnrollCourseDTO> optionalEnrollCourse =
                enrollCourseDTOS.parallelStream()
                        .filter(stdEnrollCourseDTO -> stdEnrollCourseDTO.getCode().equals(code))
                        .findFirst();

        // Optional이 비어있지 않으면 강의 정보를 가져오고, 비어있으면 null로 설정
        StdEnrollCourseDTO newCourse = optionalEnrollCourse.orElse(null);

        // 강의 정원 체크
        if (newCourse.getStatus()) {
            return "FULL"; // 정원이 가득 차면 등록하지 않음
        }

        // 강의 시간 겹침 체크
        boolean hasConflict = currentCourses.stream().anyMatch(course -> {
            return course.getWeek().equals(newCourse.getWeek()) &&
                    !course.getStarTime().isAfter(newCourse.getEndTime()) &&
                    !course.getEndTime().isBefore(newCourse.getStarTime());
        });
        if (hasConflict) {
            return "DUPLICATE"; // 시간 중복 시 등록하지 않음
        }

        // 현재 수강 중인 학점 합산
        int totalCredits = currentCourses.stream()
                .mapToInt(course -> course.getCredit())
                .sum();

        // 수강신청 시 추가될 강의의 학점을 더한 값이 전체 신청 가능한 학점(ALLCREDIT)을 초과하는지 체크
        if (totalCredits + newCourse.getCredit() > StdEnrollCourseDTO.ALLCREDIT) {
            return "CREDIT"; // 전체 신청 가능한 학점을 초과 경우 반환, 수강 등록 X
        }

        // 수강한 학점을 stdCredit에 저장
        int updatedCredits = totalCredits + newCourse.getCredit();

        // 학생의 수강 학점을 DB에 업데이트
        enrollInCourseMapper.update_student_credit(stdNo, updatedCredits);


        // 수강 정보 DB 삽입
        enrollInCourseMapper.insert_course_details(stdNo, code);
        return "SUCCESS"; // 성공적으로 등록됨
    }


    // 수강된 강의와 수강하지않은 강의들까지 전체 강의 조회하는 메소드
    public Map<String, List<StdEnrollCourseDTO>> get_course_details(Integer stdNo, Integer code, String lectureName) {
        // 수강한 과목 조회
        List<StdEnrollCourseDTO> enrollCourseDTOS = enrollInCourseMapper.select_enroll_in_course(stdNo);

        // 현재 수강 중인 강의 목록 조회
        List<StdEnrollCourseDTO> currentCourses = enrollCourseDTOS.parallelStream()
                .filter(stdEnrollCourseDTO -> stdEnrollCourseDTO.getStdNo() != null)
                .toList();

        // 학생의 현재 수강신청 내역만 따로 모으기
        List<StdEnrollCourseDTO> stdEnrollCourse = currentCourses;

        // 전체 강의 목록 조회
        List<StdEnrollCourseDTO> allEnrollCourse = enrollCourseDTOS.parallelStream()
                .filter(stdEnrollCourseDTO -> {
                    if (code == -1 || stdEnrollCourseDTO.getCode().equals(code)) {
                        return true;
                    } else if (lectureName != null && (lectureName.isEmpty() || stdEnrollCourseDTO.getLectureName().contains(lectureName))) {
                        return true;
                    }
                    return false;
                })
                .toList();

        // 두 개의 목록을 Map 형태로 반환
        return Map.of("stdEnrollCourse", stdEnrollCourse, "allEnrollCourse", allEnrollCourse);
    }

}










