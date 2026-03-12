package com.example.ems.controller;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.service.EmployeeServicee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeServicee employeeService;

    public EmployeeServicee getEmployeeService() {
        return employeeService;
    }

    public EmployeeController(EmployeeServicee employeeService) {
        this.employeeService = employeeService;
    }

    public void setEmployeeService(EmployeeServicee employeeService) {
        this.employeeService = employeeService;
    }

    //building add employee rest api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
//bulid get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id")Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new  ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    //build get all employees
    @GetMapping
    public  ResponseEntity<List<EmployeeDto>>getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees,HttpStatus.OK);

    }
    //build update employee rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable("id")Long employeeId,@RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId,updatedEmployee);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    //build delete employee rest api
    @DeleteMapping("{id}")
 public ResponseEntity<String>deleteEmployee(@PathVariable("id")long employeeId){
        employeeService.deleteEmployee(employeeId);
        return  ResponseEntity.ok("Employee Deleted Successfully");
    }
    }

