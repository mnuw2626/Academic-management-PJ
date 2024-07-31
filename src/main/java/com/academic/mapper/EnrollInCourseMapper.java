package com.academic.mapper;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.LectureDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollInCourseMapper {

    // 전체 강의 조회
    List<LectureDTO> select_all_lectures();

    // 타입, 학년, 학기, 강의 조회
    List<LectureDTO> select_all_lecture(String type, Integer grade, String name);

    // 과목코드로 강의 조회
    LectureDTO select_code_lecture(Integer code);

    // 단과대학 조회
    List<CollegeDTO> select_colleges();

    // 단과대학에 따른 학과 조회
    List<DepartmentDTO> select_dept(Integer collegeId);

    // 학과 id로 학과 조회
    DepartmentDTO select_department(Integer deptID);


}
