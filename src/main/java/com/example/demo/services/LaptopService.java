package com.example.demo.services;

import com.example.demo.entities.Laptops;
import com.example.demo.repository.LaptopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaptopService {
    @Autowired
    LaptopRepo laptopRepo;
    public Laptops updateLaptop(int laptopId, Laptops laptop) {
        Optional<Laptops>lap=laptopRepo.findById(laptopId);
        String lapName=laptop.getLaptopName();
        return lap.map(l->{
            l.setLaptopName(lapName);
            return laptopRepo.save(l);
        }).orElse(null);
    }
}
