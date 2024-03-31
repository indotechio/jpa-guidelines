package com.guidelines.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class CompanyDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal id;
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotNull
    private short companyTypeId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String companyTypeName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;
}
