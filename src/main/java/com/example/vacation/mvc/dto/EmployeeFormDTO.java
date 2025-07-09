package com.example.vacation.mvc.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeFormDTO {
    private EmployeeDTO employee;
    private EmployeeStatusDTO status;
}
