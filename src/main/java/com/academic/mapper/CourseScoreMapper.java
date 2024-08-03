package com.academic.mapper;

import com.academic.dto.CourseDetailsDTO;
import com.academic.dto.GradeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseScoreMapper {

    // 수강 내역 조회
    List<CourseDetailsDTO> select_all_enroll_in_coures_score();

    // 평점(등급) 목록 조회
    List<GradeDTO> select_grade_type();

}
