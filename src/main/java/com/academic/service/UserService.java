package com.academic.service;

import com.academic.dto.*;
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

    public int std_status(Integer no){
        StdDTO student = userMapper.select_scholarship(no);
        if (student == null){
            return 0;
        }
        else if (student.getTuitionDTO().getCheck() != 0)
        {
            return 1;
        }else {
            return 2;
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
        if (leaveDTO.getLeaveCount() == 0) {
            leaveDTO.setStatus("처리중");
            userMapper.insertLeaveApplication(leaveDTO);
        }else if (leaveDTO.getLeaveCount() != 0){
            leaveDTO.setStatus("처리중");
            userMapper.updateLeaveApplication(leaveDTO);
        }
    }

    // 유저의 휴학/복학 상태 조회
    public LeaveDTO select_user_stat(Integer no) {
        LeaveDTO leaveDTO = userMapper.select_stat(no);
        if (leaveDTO == null) {
            leaveDTO = new LeaveDTO();
            leaveDTO.setStdNo(0);
            System.out.println("LeaveDTO is null, setting stdNo to 0");
        } else {
            System.out.println("LeaveDTO found: " + leaveDTO.getStdNo());
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
        LeaveDTO return_std = userMapper.select_stat(leaveDTO.getStdNo());

        System.out.println(return_std);

        if (leaveDTO != null && "휴학".equals(return_std.getStatus())) {
            leaveDTO.setStatus("복학 처리중");
            leaveDTO.setStartDate(null);
            leaveDTO.setEndDate(null);
            leaveDTO.setReason("복학신청");
            leaveDTO.setApplicationDate(LocalDate.now());
            userMapper.updateReturnApplication(leaveDTO);
        }
    }

    /*****************공지사항*****************/
    public NoticeDTO get_notice(Integer noticeNo){
        return userMapper.select_notice(noticeNo);
    }

    public List<NoticeDTO> get_notices(String title, String searchType) {

        return userMapper.select_notices(title, searchType);
    }

    public void update_view(Integer noticeNo){
        userMapper.update_view(noticeNo);
    }

    /*****************성적조회*****************/
    public List<CourseDetailsDTO> get_score(Integer stdNo, Integer  year, Integer semester){
        return userMapper.get_score(stdNo, year, semester);
    }

}

