package com.example.vacation.common.config;

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/mngr/login","/", "/mngr/login/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/mngr/login")
                        .loginProcessingUrl("/mngr/login") // 또는 /mngr/loginProc 로 분리해도 좋음
                        .usernameParameter("empNo")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/mngr/main", true)
                        .failureUrl("/mngr/login?error=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean(name = "mngrPasswordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
