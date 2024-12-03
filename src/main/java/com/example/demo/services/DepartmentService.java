package com.example.demo.services;

import com.example.demo.entities.department;
import com.example.demo.entities.employee;
import com.example.demo.repository.departmentRepo;
import com.example.demo.repository.employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    departmentRepo DeptRepo;

    @Autowired
    employeeRepo EmpRepo;

    public department assignManagerToDept(int departmentId, int employeeId) {
        Optional<department> dept= DeptRepo.findById(departmentId);
        Optional<employee> emp= EmpRepo.findById(employeeId);

        return dept.flatMap(dep->
                emp.map(empl-> {
                    dep.setDeptManager(empl);
                    return DeptRepo.save(dep);
                })
                ).orElse(null);
    }

    public department createDept(department dept) {
        return DeptRepo.save(dept);
    }

    public List<department> getDeps() {
        return DeptRepo.findAll();
    }
}
