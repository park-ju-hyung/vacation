package com.example.vacation.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStatusVO extends BaseVO {
    private Long seq;
    private int statusId;
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String status; // 상태
    private String statusDate; // 날짜
    private String statusReason; // 사유
}
