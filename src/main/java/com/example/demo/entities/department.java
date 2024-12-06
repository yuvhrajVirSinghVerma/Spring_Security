package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    private String deptName;

    @OneToOne
    private employee deptManager;


}
