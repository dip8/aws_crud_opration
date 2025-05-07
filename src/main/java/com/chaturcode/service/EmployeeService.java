package com.chaturcode.service;

import com.chaturcode.entity.Employee;
import com.chaturcode.exception.RecordNotFoundException;
import com.chaturcode.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee signup(Employee employee){
        return employeeRepository.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword){
            boolean flag = false;

            Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);

            if(employee!=null){
                flag=true;
            }
            return flag;
    }

    public Optional<Employee> findByID(int empId){
        return Optional.ofNullable(employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFoundException("Employee Id does not exists")));
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee update(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteById(int empId){
        employeeRepository.deleteById(empId);
    }
}

