package com.guidelines.demo.controller;

import com.guidelines.demo.model.dto.CompanyDTO;
import com.guidelines.demo.model.response.DataResponse;
import com.guidelines.demo.model.response.DefaultResponse;
import com.guidelines.demo.model.response.PaginationDataResponse;
import com.guidelines.demo.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

@RestController
@RequestMapping("/company")
@Tag(name = "Company Service", description = "API Collections for Company Data")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @Operation(
            summary = "Get Company List",
            description = "fetches all company entities and their data from data source"
    )
    public ResponseEntity<PaginationDataResponse<CompanyDTO>> getList(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) int limit,
            @RequestParam(defaultValue = "createdAt", required = false) String sortField,
            @RequestParam(defaultValue = "DESC", required = false) String sortOrder
    ) {
        PaginationDataResponse<CompanyDTO> list = companyService.getDataWithPagination(page, limit, sortField, sortOrder);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Get Detailed Company Data",
            description = "fetch detailed company data from data source"
    )
    public ResponseEntity<DataResponse<CompanyDTO>> getById(@PathVariable(value = "id", required = true) BigDecimal id) {
        DataResponse<CompanyDTO> company = companyService.findOne(id);
        return ResponseEntity.ok().body(company);
    }

    @PostMapping
    @Operation(
            summary = "Create a new Company",
            description = "Create a new Company and save it into the data source"
    )
    public ResponseEntity<DataResponse<CompanyDTO>> create(@Valid @RequestBody CompanyDTO company) {
        DataResponse<CompanyDTO> data = companyService.create(company);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping(path = "/{id}")
    @Operation(
            summary = "Update an existing company data"
    )
    public ResponseEntity<DataResponse<CompanyDTO>> update(
            @Valid @RequestBody CompanyDTO company,
            @PathVariable(value = "id", required = true) BigDecimal id
    ) {
        DataResponse<CompanyDTO> data = companyService.update(id, company);
        return ResponseEntity.ok().body(data);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Delete a company data"
    )
    public ResponseEntity<DefaultResponse> delete(@PathVariable(value = "id", required = true) BigDecimal id) {
        DefaultResponse doDelete = companyService.delete(id);
        return ResponseEntity.ok().body(doDelete);
    }
}
