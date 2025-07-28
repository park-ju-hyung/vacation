package com.example.vacation.common.config;

import com.example.vacation.common.bean.MngrLoginFailureHandler;
import com.example.vacation.common.bean.MngrLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MultiSecurityConfig {

    @Bean
    public MngrLoginSuccessHandler mngrLoginSuccessHandler() {
        return new MngrLoginSuccessHandler();
    }

    @Bean
    public MngrLoginFailureHandler mngrLoginFailureHandler() {
        return new MngrLoginFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/res/css/**", "/res/js/**", "/admin/Employee/regist", "/employee/regist").permitAll()
                        .requestMatchers("/mngr/**").hasRole("MNGR")
                        .requestMatchers("/site/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("SUPER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/mngr/login")
                        .usernameParameter("empNo")
                        .passwordParameter("empPassword")
                        .successHandler(mngrLoginSuccessHandler())
                        .failureHandler(mngrLoginFailureHandler())
                        .permitAll()
                );

        return http.build();
    }

    @Bean(name = "mngrPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
