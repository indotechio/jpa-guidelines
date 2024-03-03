package com.guidelines.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "company_type")
public class CompanyType {
    @Id
    @SequenceGenerator(name = "company_type_id_seq", sequenceName = "company_type_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "company_type_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private short id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;
}
