package com.academic.service;

import com.academic.dto.StdDTO;
import com.academic.dto.UserDTO;
import com.academic.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean user_register(UserDTO userDTO){
        userDTO.setPassword(
                passwordEncoder.encode(userDTO.getPassword())
        );
        userMapper.insert_user(userDTO);
        log.info(userDTO.toString());
        return true;
    }

    public StdDTO select_user_info_service(String id){
        return userMapper.select_all_userInfo(id); // 단일 사용자 반환;
    }
    public StdDTO get_std_info(Integer stdNo){
        return userMapper.select_std_info(stdNo);
    }

    public void set_std_id(String id, Integer stdNo){
        userMapper.update_std_id(id, stdNo);
    }
}
