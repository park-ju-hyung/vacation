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
    private Long seq;
    private int holidayId;
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private Date startDate;
    private Date endDate;
    private Date regDate;
    private BigDecimal useDays;
    private String type;
    private BigDecimal totalDays;
    private String approval;
    private String appro;


}



