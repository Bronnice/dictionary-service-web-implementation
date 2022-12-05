package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.invalid;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.valid;

public interface Validator<T> extends Function<T, ValidationResult> {
    default Validator<T> holds(Predicate<T> predicate, String message){
        return request -> predicate.test(request) ? valid() : invalid(message);
    }

    default Validator<T> and(Validator<T> other) {
        return customer -> {
            final ValidationResult result = this.apply(customer);
            return result.getIsValid() ? other.apply(customer) : result;
        };
    }
}
