package com.example.ems.service;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.entity.Employee;
import com.example.ems.expection.ResourceNotFoundException;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServicee {

    private EmployeeRepo employeeRepo;

    // Constructor Injection
    public EmployeeServicee(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    // Create Employee
    public EmployeeDto createEmployee(EmployeeDto employeeDto){

        System.out.println("Employee received: " + employeeDto.getFirstName());

        // Convert DTO -> Entity
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        // Save to database
        Employee savedEmployee = employeeRepo.save(employee);

        System.out.println("Saved ID: " + savedEmployee.getId());

        // Convert Entity -> DTO
        return new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );
    }
    public  EmployeeDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not exists with given id:"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepo.findAll();

        return employees.stream().map((employee) -> EmployeeMapper .mapToEmployeeDto(employee)).collect(Collectors.toList());
    }
    public EmployeeDto updateEmployee(Long employeeId,EmployeeDto updateEmployee){
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id:"+ employeeId));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Employee updatedEmployee = employeeRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);

    }
    public void deleteEmployee(Long employeeId){
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id:"+ employeeId));
        employeeRepo.deleteById(employeeId);

    }
}