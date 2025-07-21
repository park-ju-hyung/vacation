package com.example.vacation.mvc.vo;

import com.example.vacation.mvc.dto.BreakDTO;
import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BreakFormVO extends BaseVO {
    private EmployeeVO employee;
    private BreakVO breakData;
}



