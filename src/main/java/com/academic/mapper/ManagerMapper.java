package com.academic.mapper;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    void insert_std(StdDTO stdDTO);
    List<StdDTO> select_std(StdDTO stdDTO);

    List<CollegeDTO> select_colleges();

    List<DepartmentDTO> select_dept(Integer collegeId);
}
