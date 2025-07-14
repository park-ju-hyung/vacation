package com.example.vacation.mvc.vo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeVO extends BaseVO implements Serializable, UserDetails {
    private Long seq;
    private int empId;
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private String hireDate; // 입사일
    private String email; // 이메일
    private String status; // 상태
    private String empPassword; // 패스워드

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(empNo));
    }

    @Override
    public String getUsername() {
        return this.empNo; // 사번을 로그인 ID로 사용
    }

    @Override
    public String getPassword() {
        return this.empPassword; // 암호화된 비밀번호 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 관리 안 할 경우 true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 관리 안 할 경우 true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 유효성 관리 안 할 경우 true
    }

    @Override
    public boolean isEnabled() {
        return "재직".equals(this.status); // 재직 상태만 로그인 가능
    }

}



