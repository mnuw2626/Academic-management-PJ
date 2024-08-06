package com.academic.service;

import com.academic.dto.*;

import com.academic.dto.*;

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
    //학생 전체 명단 조회
    public List<StdDTO> std_all_list(){
        return managerMapper.select_all_std();
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

    // 모든 휴학 신청 정보를 가져오기
    public List<LeaveDTO> get_all_leaves_info() {
        return managerMapper.select_all_std_leaves();
    }

    // 모든 복학 신청 정보를 가져오기
    public List<LeaveDTO> get_all_returns_info() {
        return managerMapper.select_all_std_returns();
    }

    // 휴학 상태 업데이트
    public boolean update_leave_status(Integer stdNo) {
        int updatedRows = managerMapper.update_leave_std_status(stdNo);
        return updatedRows > 0; // 업데이트된 행이 있으면 true 반환, 없으면 false 반환
    }

    // 복학 상태 업데이트
    public boolean update_return_status(Integer stdNo) {
        int updatedRows = managerMapper.update_return_std_status(stdNo);
        return updatedRows > 0; // 업데이트된 행이 있으면 true 반환, 없으면 false 반환
    }

}
