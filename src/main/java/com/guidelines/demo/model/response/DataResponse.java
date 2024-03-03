package com.guidelines.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataResponse<T> {
    boolean status = true;
    String message = "Data fetched";
    T data;
}
