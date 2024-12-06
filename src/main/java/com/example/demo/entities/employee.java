package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    private String EmpName;

    @OneToOne(mappedBy = "deptManager")
    @JsonIgnore
    private department dept;

    @OneToMany(mappedBy = "employee")
    List<Laptops> laptops;

}
