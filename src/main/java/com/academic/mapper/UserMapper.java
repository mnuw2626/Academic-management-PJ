package com.academic.mapper;

import com.academic.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO select_userInfo(String id);
    void insert_user(UserDTO userDTO);
}
