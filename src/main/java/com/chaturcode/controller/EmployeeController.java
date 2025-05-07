package com.chaturcode.controller;

import com.chaturcode.entity.Employee;
import com.chaturcode.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signup(@RequestBody Employee employee){
        log.info("@@@Try to sign up for the Employee"+ employee.getEmpName());
        return ResponseEntity.ok(employeeService.signup(employee));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        return ResponseEntity.ok(employeeService.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId){
        return ResponseEntity.ok(employeeService.findByID(empId));
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<Employee>> findByName(
            @RequestParam(defaultValue = "CHATURCODE", required = false) String empName) {

        List<Employee> filtered = employeeService.findAll().stream()
                .filter(emp -> emp.getEmpName().equals(empName))
                .toList();

        return ResponseEntity.ok(filtered);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId, @RequestBody Employee employee){
        Employee employee1 = employeeService.findByID(empId).get();
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return ResponseEntity.ok(employeeService.update(employee1));
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Data Deleted by Successfully");
    }
}
