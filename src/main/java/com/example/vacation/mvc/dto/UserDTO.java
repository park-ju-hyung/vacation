package com.example.vacation.mvc.dto;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long userIdx;
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
	
	private String userDiv;
	private String regDate;
	private String modifyDate;
	
	// 추가된 필드
	private String errorCode;
}
