package com.example.demo.controller;

import com.example.demo.entities.Laptops;
import com.example.demo.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LaptopController {
    @Autowired
    LaptopService laptopServ;
    @PutMapping(path = "/updateLaptop/{laptopId}")
    public Laptops updateLaptop(@PathVariable int laptopId, @RequestBody Laptops laptop){
        return laptopServ.updateLaptop(laptopId,laptop);
    }
}
