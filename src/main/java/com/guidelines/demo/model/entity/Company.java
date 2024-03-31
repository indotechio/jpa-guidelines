package com.guidelines.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "company")
public class Company {
    @Id
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "company_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private BigDecimal id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_TYPE", referencedColumnName="ID", nullable = false)
    private CompanyType companyType;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
}
