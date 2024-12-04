package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Laptops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int LaptopId;

    String laptopName;

    @ManyToOne
    @JsonIgnore
    employee employee;
}
