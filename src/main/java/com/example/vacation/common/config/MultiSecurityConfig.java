package com.example.vacation.common.config;

import com.example.vacation.common.bean.MngrLoginFailureHandler;
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
    public MngrLoginFailureHandler mngrLoginFailureHandler() {
        return new MngrLoginFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","res/css/**", "res/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/mngr/login") // 또는 /mngr/loginProc 로 분리해도 좋음
                        .usernameParameter("empNo")
                        .passwordParameter("empPassword")
                        .defaultSuccessUrl("/site/informodify/Information_modify", true)
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
