package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.DictionaryRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.KeyRequest;
import org.springframework.http.HttpStatus;

import java.util.function.Predicate;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.invalid;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.valid;

public interface KeyValidator extends Validator<KeyRequest>{
    static KeyValidator holds(Predicate<KeyRequest> predicate, String message){
        return request -> predicate.test(request) ? valid() : invalid(message);
    }

    static KeyValidator keyLength(int keyLength) {
        return holds(request -> request.getKey().length() < keyLength,
                HttpStatus.BAD_REQUEST.value() + " Key is longer then " + keyLength);
    }

    default KeyValidator and(KeyValidator other) {
        return customer -> {
            final ValidationResult result = apply(customer);
            return result.getIsValid() ? other.apply(customer) : result;
        };
    }
}
