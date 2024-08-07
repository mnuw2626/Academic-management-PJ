package com.academic.config;

import com.academic.handler.LoginSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(config -> {
            config.loginPage("/user/login") //내가 쓰는 로그인 페이지 GETMAPPING경로
//            .loginProcessingUrl("/user/login") //로그인을 시도하는 POST Mapping 경로
            .usernameParameter("id") //로그인에서 아이디 input의 name값
//            .passwordParameter("password") //비밀번호 input의 name값
//            .defaultSuccessUrl("/main") //로그인 성공시 이동할 getmapping경로
            .successHandler(new LoginSuccessHandler()) //로그인 성공 시 로그인 성공 핸들러를 통해 main으로 이동할것인지 manager_main으로 이동할것인지 결정
            .permitAll();

        });

//        로그아웃 기능
        http.logout(config -> {
            config.logoutUrl("/user/logout")
                    .logoutSuccessUrl("/user/login")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true) //세션 삭제 -> 현재 세션 삭제 안되는듯. 문의필요
                    .permitAll();
        });

        http.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/manager/**", "/manager_main").hasAuthority("MANAGER")
                    .requestMatchers("/main", "/scholarship/**","/school/**").hasAuthority("STUDENT")
                    .requestMatchers("/user/login","/user/register", "/user/student-number", "/user/setregister").permitAll()
                    .anyRequest().authenticated();
//            registry.anyRequest().permitAll();
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
