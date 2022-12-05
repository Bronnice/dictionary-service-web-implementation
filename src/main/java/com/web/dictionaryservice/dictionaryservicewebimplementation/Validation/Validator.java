package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.SignupRequest;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.invalid;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.valid;

public interface Validator<T> extends Function<T, ValidationResult> {
}
