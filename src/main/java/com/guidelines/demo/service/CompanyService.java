package com.guidelines.demo.service;

import com.guidelines.demo.model.dto.CompanyDTO;
import com.guidelines.demo.model.response.DataResponse;
import com.guidelines.demo.model.response.DefaultResponse;
import com.guidelines.demo.model.response.PaginationDataResponse;

import java.math.BigDecimal;

public interface CompanyService {
    PaginationDataResponse<CompanyDTO> getDataWithPagination(int page, int limit, String sortField, String sortOrder);
    DataResponse<CompanyDTO> findOne(BigDecimal id);
    DataResponse<CompanyDTO> create(CompanyDTO company);
    DataResponse<CompanyDTO> update(BigDecimal id, CompanyDTO company);
    DefaultResponse delete(BigDecimal id);
}
