package com.academic.mapper;

import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO select_userInfo(String id);

    void insert_user(UserDTO userDTO);

    /**
     * @param id
     * @return
     */
    StdDTO select_all_userInfo(String id);

    StdDTO select_std_info(Integer stdNo);

    void update_std_id(String id, Integer stdNo);
}
