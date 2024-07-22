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
            .successHandler(new LoginSuccessHandler())
            .permitAll();

        });

        http.logout(config -> {
            config.logoutUrl("/logout")
                    .logoutSuccessUrl("/main")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .permitAll();
        });

        http.authorizeHttpRequests(registry -> {
            registry.requestMatchers("/main", "user/register").permitAll()
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
