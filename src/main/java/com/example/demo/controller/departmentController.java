package com.example.demo.controller;

import com.example.demo.entities.department;
import com.example.demo.entities.employee;
import com.example.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class departmentController {

    @Autowired
    DepartmentService deptServ;

    @GetMapping(path = "/getDeps")
    public List<department> getDeps(){
        return deptServ.getDeps();
    }
    @PostMapping(path = "/department")
    public department createDept(@RequestBody department dept){
        return deptServ.createDept(dept);
    }
    @PutMapping(path = "{departmentId}/manager/{EmployeeId}")
    public department assignDepartment(@PathVariable int departmentId,@PathVariable int EmployeeId){
        return deptServ.assignManagerToDept(departmentId,EmployeeId);
    }
}
