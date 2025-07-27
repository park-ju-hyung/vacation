package com.example.vacation.mvc.vo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BreakVO extends BaseVO {
    private Long seq; // 순번
    private int holidayId; // 아이디
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private String startDate; // 휴가1
    private String endDate; // 휴가2
    private BigDecimal useDays; // 신청 휴가 일수
    private String type; // 연차 or 반차
    private BigDecimal totalDays; // 부여받은 휴가 일수
    private String reason; // 사유
    private String approval; // 승인 여부
    private Date regDate; // 신청 일자
}



