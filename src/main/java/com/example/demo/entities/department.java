package com.example.demo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    private String deptName;

    @OneToOne
    private employee deptManager;


}
