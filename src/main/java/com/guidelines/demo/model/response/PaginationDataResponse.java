package com.guidelines.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginationDataResponse<T> {
    boolean status = true;
    String message = "Data fetched";
    int page;
    int limit;
    BigInteger total;
    List<T> data;
}
