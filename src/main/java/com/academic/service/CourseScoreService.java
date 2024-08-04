package com.academic.service;

import com.academic.dto.CourseDetailsDTO;
import com.academic.dto.GradeDTO;
import com.academic.mapper.CourseScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseScoreService {
    @Autowired
    CourseScoreMapper courseScoreMapper;

    // 수강 내역 조회
    public List<CourseDetailsDTO> get_all_grade_score(){
        return courseScoreMapper.select_all_enroll_in_coures_score();
    }

    // 등급 목록 조회
    public List<GradeDTO> get_all_grade(){
        return courseScoreMapper.select_grade_type();
    }

    public void update_std_enroll_coures(Integer stdNo, Integer code, String grade){
        courseScoreMapper.update_course_std_grade(stdNo, code, grade);
    }

}
