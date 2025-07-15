package com.example.vacation.mvc.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeDTO extends BaseDTO {
    private Long seq;
    private int empId;
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private String hireDate; // 입사일
    private String email; // 이메일
    private String status ="재직"; // 상태
    private String empPassword; // 패스워드
    private String role; // 권한
}
