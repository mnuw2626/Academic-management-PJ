package com.academic.mapper;

import com.academic.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollInCourseMapper {

    // 전체 강의 조회
    List<LectureDTO> select_all_lectures();

    // 타입, 학년, 학기, 강의 조회
    List<LectureDTO> select_all_lecture(String type, Integer grade, String name);

    // 과목코드로 강의 조회
    List<LectureDTO> select_code_lecture(Integer code);

    // 단과대학 조회
    List<CollegeDTO> select_colleges();

    // 단과대학에 따른 학과 조회
    List<DepartmentDTO> select_dept(Integer collegeId);

    // 모든 학과 조회
    List<DepartmentDTO> select_all_dept();

    // 강의 조회
    List<LectureDTO> select_all_lecture(String type, Integer grade, Integer semester, String name);

    // 학과 id로 학과 조회
    DepartmentDTO select_department(Integer deptID);

    // 수강 내역에 추가
    void insert_course_details(Integer stdNo, Integer code);

    // 강의들 조회
    List<StdEnrollCourseDTO> select_enroll_in_course(Integer stdNo);

    // 강의 신청 인원 카운트 업데이트
    void update_lecture_num_of_student(Integer code);

    // 학생의 현재 수강 중인 강의 조회(요일, 시작시간, 종료시간)
    List<StdEnrollCourseDTO> select_std_current_courses(Integer stdNo);

    // 과목 코드로 강의 정보 조회(요일, 시작시간, 종료시간)
    StdEnrollCourseDTO select_get_course_details(Integer code);

    String select_lecture_name_by_code(Integer code);

    void update_student_credit(Integer stdNo, int updatedCredits);

}
