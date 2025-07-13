package com.example.vacation.mvc.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeStatusDTO extends BaseDTO {
    private Long seq;
    private int statusId;
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String status; // 상태
    private String statusDate; // 퇴직일
    private String statusReason; // 사유
    private String breakStart; // 휴직 시작일
    private String breakEnd; // 휴직 마지막일
}
