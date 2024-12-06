package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

//@Embeddable
@EntityListeners(AuditingEntityListener.class)
@Audited
public class AuditingBaseClass {

    @CreatedBy
    //for this auditorware need to be used
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

}
