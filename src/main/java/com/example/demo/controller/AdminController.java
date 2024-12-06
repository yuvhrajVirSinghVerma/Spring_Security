package com.example.demo.controller;

import com.example.demo.entities.Laptops;
import jakarta.persistence.EntityManager;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/admin/audit")
public class AdminController {

    @Autowired
    EntityManager entityManager;
    @GetMapping(path = "/laptop/{laptopId}")
    public List<Laptops> getLaptopAudit(@PathVariable int laptopId){
        AuditReader reader= AuditReaderFactory.get(entityManager);
        List<Number>l=reader.getRevisions(Laptops.class,laptopId);
        System.out.println("revisions "+l);
        return l.stream()
                .map(revNum->reader.find(Laptops.class,laptopId,revNum))
                .collect(Collectors.toList());//this gives error when we try to assign employee a laptop
    }
}
