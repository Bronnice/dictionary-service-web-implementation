package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ValidationResult {

    Boolean isValid;
    String errorMessage;

    public ValidationResult(Boolean isValid) {
        this.isValid = isValid;
    }

    static ValidationResult valid() {
        return new ValidationResult(true);
    }

    static ValidationResult invalid(String errorMessage) {
        return new ValidationResult(false, errorMessage);
    }
}
