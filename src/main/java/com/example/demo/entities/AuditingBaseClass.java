package com.example.demo.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@MappedSuperclass

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

//@Embeddable
public class AuditingBaseClass {

    @CreatedBy
    //for this auditorware need to be used
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

}
