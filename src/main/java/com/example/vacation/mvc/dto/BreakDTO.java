package com.example.vacation.mvc.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BreakDTO extends BaseDTO {
    private Long seq;
    private int holidayId;
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private Date startDate;
    private Date endDate;
    private BigDecimal useDays;
    private String type;
    private BigDecimal totalDays;
    private String approval;
}
