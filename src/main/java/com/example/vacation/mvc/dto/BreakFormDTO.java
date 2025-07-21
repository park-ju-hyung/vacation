package com.example.vacation.mvc.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BreakFormDTO{
    private EmployeeDTO employee;
    private BreakDTO breakData;
}
