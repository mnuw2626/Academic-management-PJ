package com.academic.service;

import com.academic.dto.UserDTO;
import com.academic.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Login창에서 로그인 버튼 누르면 여기로 자동으로 온다
//loadUserByUsername이 자동실행됨
// String username <- 여기에 로그인창에서 입력한 사용자 id값 들어옴
@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //여긴 비밀번호없다. username으로 db에서 해당 id의 유저를 찾음
        System.out.println("로그인 할려하는 유저명: " + username);
        // usermapper 같은 Mapper를 써서 userDTO를 찾는다
        UserDTO findUser = userMapper.select_userInfo(username);
        log.info("찾은 유저 정보 :" + findUser);
        // 유저가 없다면
        if (findUser == null){
            throw new UsernameNotFoundException("user not found");
        }
        // 유저 있으면 성공
        return findUser;
    }
}
