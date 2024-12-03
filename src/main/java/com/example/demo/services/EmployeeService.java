package com.example.demo.services;

import com.example.demo.entities.department;
import com.example.demo.entities.employee;
import com.example.demo.repository.employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    employeeRepo empRepo;


    public employee createEmp(employee emp) {
        return empRepo.save(emp);
    }

    public List<employee> getEmps() {
        return empRepo.findAll();
    }
}
