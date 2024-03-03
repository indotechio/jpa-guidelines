package com.guidelines.demo.repository;

import com.guidelines.demo.model.entity.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyTypeRepository extends JpaRepository<CompanyType, Short> {
}
