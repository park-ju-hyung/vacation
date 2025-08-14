package com.example.vacation.mvc.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BreakListDTO extends BaseDTO {
    private Long seq; // 순번
    private Long origSeq; // 순번
    private String empName; // 직원 이름
    private String empBirth; // 직원 생년월일
    private String empNo; // 사번
    private String position; // 직책
    private String department; // 부서
    private String startDate; // 휴가1
    private String endDate; // 휴가2
    private BigDecimal useDays; // 신청 휴가 일수
    private String type; // 연차 or 반차
    private String reason; // 사유
    private String approval = "제출";; // 승인 여부
    private String status = "002"; // 상태 코드
    private String regDate; // 신청 일자
    private String rejectReason; // 반려사유
    private String vacationType;
    private String specialType;


}
