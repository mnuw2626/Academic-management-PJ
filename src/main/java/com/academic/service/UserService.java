package com.academic.service;

import com.academic.dto.LeaveDTO;
import com.academic.dto.NoticeDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.TuitionDTO;
import com.academic.dto.UserDTO;
import com.academic.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void user_register(UserDTO userDTO){
        userDTO.setPassword(
                passwordEncoder.encode(userDTO.getPassword())
        );
        userMapper.insert_user(userDTO);
        log.info(userDTO.toString());
    }

    public StdDTO select_user_info_service(String id){
        return userMapper.select_all_userInfo(id); // 단일 사용자 반환;
    }

    // 학생 id 업데이트
    public void set_std_id(String id, Integer stdNo){
        userMapper.update_std_id(id, stdNo);
    }

    public StdDTO get_std_info(int studentNo) {
        return userMapper.select_std_info(studentNo);
    }

    /**************등록금 정보 가져오기***************/
    public StdDTO select_user_scholarship(Integer no){
        return userMapper.select_scholarship(no);
    }

    public boolean std_status(Integer no){
        StdDTO student = userMapper.select_scholarship(no);
        int scholarship_ok = student.getTuitionDTO().getStatus();
        if (scholarship_ok == 0)
        {
            return false;
        }else {
            return true;
        }
    }

    /**************등록금 납부***************/
    public void update_scholarship(Integer no){
        userMapper.update_std_scholarship(no);
    }


    /**************휴학***************/
    // 현재 날짜가 휴학 신청 기간인지 체크
    public boolean isLeaveApplicationPeriod(LocalDate currentDate) {
        int month = currentDate.getMonthValue();
        return (month == 7 || month == 8 || month == 1 || month == 2);
    }

    public void insertLeaveApplication(LeaveDTO leaveDTO) {
        if (leaveDTO != null) {
            leaveDTO.setStatus("처리중");
            userMapper.insertLeaveApplication(leaveDTO);
        }
    }

    // 유저의 휴학/복학 상태 조회
    public LeaveDTO select_user_stat(Integer no) {
        LeaveDTO leaveDTO = userMapper.select_stat(no);
        if (leaveDTO == null) {
            leaveDTO = new LeaveDTO();
            leaveDTO.setStdNo(0);
        }
        return leaveDTO;
    }

    /**************복학***************/
    // 현재 날짜가 복학 신청 기간인지 체크
    public boolean isReturnApplicationPeriod(LocalDate currentDate) {
        int month = currentDate.getMonthValue();
        return (month == 7 || month == 8 || month == 1 || month == 2);
    }

    public void insertReturnApplication(LeaveDTO leaveDTO) {
        LeaveDTO currentStatus = select_user_stat(leaveDTO.getStdNo());

        if (leaveDTO != null && "휴학".equals(currentStatus.getStatus())) {
            leaveDTO.setStatus("복학 처리중");
            leaveDTO.setStartDate(null);
            leaveDTO.setEndDate(null);
            leaveDTO.setReason("복학신청");
            userMapper.updateReturnApplication(leaveDTO);
        }
    }

    public NoticeDTO get_notice(String noticeNo){
        return userMapper.select_notice(noticeNo);
    }

    /*****************공지사항*****************/
    public List<NoticeDTO> get_notices() {
        return userMapper.select_notices();
    }

}

