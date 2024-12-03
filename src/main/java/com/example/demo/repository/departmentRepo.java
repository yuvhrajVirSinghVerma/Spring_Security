package com.example.demo.repository;

import com.example.demo.entities.department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface departmentRepo extends JpaRepository<department,Integer> {
}
