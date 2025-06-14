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
public class UserVO extends BaseVO implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	private static final String authority = "USER";

	private String userId;
	private String userPasswd;
	private String userName;
	private String userHPhone;
	private String userPhone;
	private String userEmail;
	private String userComp;
	private String userDept;
	private String userPosition;
	private String newsReceive;
	private String regDate;
	private String modifyDate;
	
	private String errorCode;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();    
		authorities.add(new SimpleGrantedAuthority(authority));	
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.userPasswd;
	}
	
	@Override
	public String getUsername() {
		return this.userName;
	}
	
	// 계정 만료여부 알림 설정
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	
	// 잠금상태 알림 설정
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 자격 유효여부 확인 설정.
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 활성여부 설정. 비활성 유저는 인증 불가.
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
