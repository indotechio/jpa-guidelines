package com.guidelines.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorsResponse {
    boolean status;
    String message;
    List<String> errors;
}
