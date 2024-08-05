package com.academic.mapper;

import com.academic.dto.LeaveDTO;
import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO select_userInfo(String id);

    void insert_user(UserDTO userDTO);

    StdDTO select_all_userInfo(String id);

    StdDTO select_scholarship(Integer no);

    void update_std_scholarship(Integer no);

    void insertLeaveApplication(LeaveDTO leaveDTO);

    LeaveDTO select_stat(Integer no);

    void updateReturnApplication(LeaveDTO leaveDTO);


}
