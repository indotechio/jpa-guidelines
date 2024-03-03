package com.guidelines.demo.repository;

import com.guidelines.demo.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;

public interface CompanyRepository extends JpaRepository<Company, BigDecimal>, JpaSpecificationExecutor<Company> {

}
