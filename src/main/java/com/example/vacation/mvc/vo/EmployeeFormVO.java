package com.example.vacation.mvc.vo;

import com.example.vacation.mvc.dto.EmployeeDTO;
import com.example.vacation.mvc.dto.EmployeeStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFormVO extends BaseVO {
    private EmployeeDTO employee;
    private EmployeeStatusDTO status;
}
