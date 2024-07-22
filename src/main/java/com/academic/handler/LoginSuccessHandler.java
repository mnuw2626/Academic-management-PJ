package com.academic.handler;

import com.academic.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDTO userDTO = (UserDTO)authentication.getPrincipal();
        int no = userDTO.getNo();
        if(no == 1111) {
            response.sendRedirect("/manager_main");
        }
        else{
            response.sendRedirect("/main");
        }
    }
}
