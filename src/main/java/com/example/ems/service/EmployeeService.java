package com.example.ems.service;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId,EmployeeDto updateEmployee);
    void deleteEmployee(Long employeeId);
}
