package com.example.demo.repository;

import com.example.demo.entities.Laptops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptops,Integer> {
}
