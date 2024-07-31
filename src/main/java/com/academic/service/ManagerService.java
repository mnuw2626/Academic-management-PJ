package com.academic.service;

import com.academic.dto.CollegeDTO;
import com.academic.dto.DepartmentDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.TuitionDTO;
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

    //학생 개개인 명단 조회
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


    //    단과대학 정보 조회 - 단과대학 DB에서 가져옴
    public List<CollegeDTO> get_colleges() {
        return managerMapper.select_colleges();
    }

    //    단과대학에 따른 학과 조회
    public List<DepartmentDTO> get_departments(Integer collegeId) {
        return managerMapper.select_dept(collegeId);
    }

    // 단과대별 등록금 가져오기
    public List<TuitionDTO> get_tuitions() {
        return managerMapper.select_std_scholarship();
    }

    // 등록금 내역 발송
    public void send_scholarship(
            List<TuitionDTO> tuitionDTOS
    ){
        managerMapper.insert_scholarship(tuitionDTOS);
    }

    // 모든 학생 등록금 제출 내역 조회
    public List<StdDTO> get_all_std_tuitions(){
        return managerMapper.select_all_std_tuition();
    }
}
