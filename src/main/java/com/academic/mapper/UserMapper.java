package com.academic.mapper;

import com.academic.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO select_userInfo(String id);

    void insert_user(UserDTO userDTO);

    StdDTO select_all_userInfo(String id);

    StdDTO select_scholarship(Integer no);

    void update_std_scholarship(Integer no);
    //휴학
    void insertLeaveApplication(LeaveDTO leaveDTO);

    void updateLeaveApplication(LeaveDTO leaveDTO);

    LeaveDTO select_stat(Integer no);
    //복학
    void updateReturnApplication(LeaveDTO leaveDTO);

    StdDTO select_std_info(Integer stdNo);

    void update_std_id(String id, Integer stdNo);

    //공지사항
    List<NoticeDTO> select_notices(String title, String searchType);

    NoticeDTO select_notice(Integer noticeNo);

    void update_view(Integer noticeNo);

    //성적조회
    List<CourseDetailsDTO> get_score(
            @Param("stdNo") Integer stdNo,
            @Param("year") Integer year,
            @Param("semester") Integer semester
    );
}
