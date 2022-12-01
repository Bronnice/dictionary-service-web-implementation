package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppError {
    private int statusCode;
    private String message;
}
