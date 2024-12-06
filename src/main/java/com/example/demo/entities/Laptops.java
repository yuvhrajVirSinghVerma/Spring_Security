package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Audited
public class Laptops extends AuditingBaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int LaptopId;

    String laptopName;

    @ManyToOne
    @JsonIgnore
    employee employee;

//    AuditingBaseClass baseclass;
}
