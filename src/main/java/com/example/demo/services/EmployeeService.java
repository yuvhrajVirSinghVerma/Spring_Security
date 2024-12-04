package com.example.demo.services;

import com.example.demo.entities.Laptops;
import com.example.demo.entities.employee;
import com.example.demo.repository.LaptopRepo;
import com.example.demo.repository.employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    employeeRepo empRepo;

    @Autowired
    LaptopRepo laptopRepo;

    public employee createEmp(employee emp) {
        return empRepo.save(emp);
    }

    public List<employee> getEmps() {
        return empRepo.findAll();
    }

    public employee addLaptops(int empId, int laptopId) {
        Optional<employee>emp=empRepo.findById(empId);
        Optional<Laptops>laptop=laptopRepo.findById(laptopId);
        List<Laptops>l=emp.get().getLaptops();
        l.add(laptop.orElse(null));

       return emp.flatMap(e ->
           laptop.map(lap->{
               lap.setEmployee(e);
           e.setLaptops(l);
           return empRepo.save(e);
           })
        ).orElse(null);
    }

    public Laptops createLaptop(Laptops laptop) {
        return laptopRepo.save(laptop);
    }
}
