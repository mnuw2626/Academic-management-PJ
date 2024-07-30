package com.academic.mapper;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper {
    void insert_std(StdDTO stdDTO);
    List<StdDTO> select_std(
            //param => 여러개니까 써줘야함
            @Param("collegeId") Integer collegeId,
            @Param("deptId") Integer deptId,
            @Param("grade") Integer grade,
            @Param("semester") Integer semester,
            @Param("name") String name,
            @Param("stdNo") String stdNo
    );
}
