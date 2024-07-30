package com.academic.service;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import com.academic.mapper.ManagerMapper;
import com.fasterxml.jackson.databind.BeanProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;

    public boolean manager_add_std(StdDTO stdDTO) {
        managerMapper.insert_std(stdDTO);
        return true;
    }

    public List<StdDTO> manager_std_list_check(
            Integer collegeId,
            Integer deptId,
            Integer grade,
            Integer semester,
            String name,
            String stdNo
    ) {
        return managerMapper.select_std(collegeId, deptId, grade, semester, name, stdNo);
    }

}
