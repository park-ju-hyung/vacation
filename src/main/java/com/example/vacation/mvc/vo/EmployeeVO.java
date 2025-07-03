package com.example.vacation.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO extends BaseVO {
    private Long seq;
    private int userId;
    private String userName; // 직원 이름
    private String userBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private String hireDate; // 입사일
    private String email; // 이메일
    private String status; // 상태
    private String userPassword; // 상태
}
