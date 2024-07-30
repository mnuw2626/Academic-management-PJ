package com.academic.mapper;

import com.academic.dto.LectureDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollInCourseMapper {

    List<LectureDTO> select_all_lectures();
}
