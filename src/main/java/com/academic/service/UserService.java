package com.academic.service;

import com.academic.dto.UserDTO;
import com.academic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return true;
    }
}
