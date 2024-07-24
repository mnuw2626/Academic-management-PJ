package com.academic.service;

import com.academic.dto.UserDTO;
import com.academic.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
