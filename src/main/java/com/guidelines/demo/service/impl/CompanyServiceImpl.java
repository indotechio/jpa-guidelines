package com.guidelines.demo.service.impl;

import com.guidelines.demo.exception.custom.NotFoundException;
import com.guidelines.demo.model.dto.CompanyDTO;
import com.guidelines.demo.model.entity.Company;
import com.guidelines.demo.model.entity.CompanyType;
import com.guidelines.demo.model.response.DataResponse;
import com.guidelines.demo.model.response.DefaultResponse;
import com.guidelines.demo.model.response.PaginationDataResponse;
import com.guidelines.demo.model.response.ResponseMessage;
import com.guidelines.demo.repository.CompanyRepository;
import com.guidelines.demo.repository.CompanyTypeRepository;
import com.guidelines.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyTypeRepository companyTypeRepository;
    private static final Logger log = LogManager.getLogger(CompanyServiceImpl.class);

    @Override
    public PaginationDataResponse<CompanyDTO> getDataWithPagination(int page, int limit, String sortField, String sortOrder) {
        String[] allowedOrder = {"createdAt"};
        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
        if ( Arrays.asList(allowedOrder).contains(sortField) ) {
            sort = sortOrder.equalsIgnoreCase("ASC")  ? Sort.by(Sort.Direction.ASC, sortField) : Sort.by(Sort.Direction.DESC, sortField);
        }
        Pageable pageable = PageRequest.of(page -1, limit, sort);
        Page<Company> companies = companyRepository.findAll(pageable);
        return toPaginationDataDTO(companies, page);
    }

    @Override
    public DataResponse<CompanyDTO> findOne(BigDecimal id) {
        try {
            Optional<Company> company = companyRepository.findById(id);
            if ( company.isPresent() ) {
                return new DataResponse<>(true, ResponseMessage.DATA_FETCHED, toDTO(company.get()));
            } else {
                throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error when get company data by id.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<CompanyDTO> create(CompanyDTO company) {
        try {
            Company dataInsert = new Company();
            dataInsert.setCreatedAt(Timestamp.from(Instant.now()));

            Company data = companyRepository.save(toEntity(company, dataInsert));
            return new DataResponse<>(true, ResponseMessage.DATA_CREATED, toDTO(data));
        } catch (Exception e) {
            log.error("Error when create company data.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<CompanyDTO> update(BigDecimal id, CompanyDTO company) {
        try {
            Optional<Company> dataUpdate = companyRepository.findById(id);
            if ( dataUpdate.isPresent() ) {
                Company data = companyRepository.save(toEntity(company, dataUpdate.get()));
                return new DataResponse<>(true, ResponseMessage.DATA_CREATED, toDTO(data));
            } else {
                throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error when update company data by id.", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(BigDecimal id) {
        try {
            companyRepository.deleteById(id);
            return new DefaultResponse(true, ResponseMessage.DATA_DELETED);
        } catch (Exception e) {
            log.error("Error when delete a company.", e);
            throw e;
        }
    }

    private CompanyDTO toDTO(Company data) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(data.getId());
        companyDTO.setName(data.getName());
        companyDTO.setCompanyTypeId(data.getCompanyType().getId());
        companyDTO.setCompanyTypeName(data.getCompanyType().getName());
        companyDTO.setCreatedAt(data.getCreatedAt());

        return companyDTO;
    }

    private Company toEntity(CompanyDTO source, Company dest) {
        Optional<CompanyType> companyType = companyTypeRepository.findById(source.getCompanyTypeId());

        dest.setCompanyType(companyType.orElse(null));
        dest.setName(source.getName());

        return dest;
    }

    private PaginationDataResponse<CompanyDTO> toPaginationDataDTO(Page<Company> datas, int page) {
        List<CompanyDTO> list = datas.getContent().stream().map(this::toDTO).collect( Collectors.toList() );
        return new PaginationDataResponse<>(
                true, ResponseMessage.DATA_FETCHED, page, datas.getSize(),
                BigInteger.valueOf(datas.getTotalElements()), list
        );
    }
}
