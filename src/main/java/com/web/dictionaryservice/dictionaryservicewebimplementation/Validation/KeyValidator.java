package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.KeyRequest;
import org.springframework.http.HttpStatus;

import java.util.function.Predicate;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.invalid;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.valid;

public interface KeyValidator extends Validator<KeyRequest> {
    static KeyValidator holds(Predicate<KeyRequest> predicate, String message) {
        return request -> predicate.test(request) ? valid() : invalid(message);
    }

    static KeyValidator keyIsNotEmpty() {
        return holds(request -> !request.getKey().trim().isEmpty(),
                HttpStatus.BAD_REQUEST.value() + " Key is empty");
    }

    static KeyValidator keyLength(int keyLength) {
        return holds(request -> request.getKey().length() < keyLength,
                HttpStatus.BAD_REQUEST.value() + " Key is longer then " + keyLength);
    }

    static KeyValidator keyWordType(WordType wordType) {
        switch (wordType) {
            case EnglishWithNums -> {
                return holds(request -> !request.getKey().matches("^[a-zA-Z0-9]$"),
                        HttpStatus.BAD_REQUEST.value() + " Key contains non english letters");
            }
            case EnglishNoNums -> {
                return holds(request -> !request.getKey().matches("^[a-zA-Z]$"),
                        HttpStatus.BAD_REQUEST.value() + " Key contains numbers or non english letters");
            }
            case OnlyNums -> {
                return holds(request -> !request.getKey().matches("^[0-9]$"),
                        HttpStatus.BAD_REQUEST.value() + " Key contains letters");
            }
        }
            return (KeyValidator) invalid(HttpStatus.BAD_REQUEST.value() + " Key contains letters");
    }

    static KeyValidator keyIsNotNull() {
        return holds(request -> request.getKey() != null, HttpStatus.BAD_REQUEST.value() + " Key is null");
    }

    default KeyValidator and(KeyValidator other) {
        return customer -> {
            final ValidationResult result = apply(customer);
            return result.getIsValid() ? other.apply(customer) : result;
        };
    }
}
