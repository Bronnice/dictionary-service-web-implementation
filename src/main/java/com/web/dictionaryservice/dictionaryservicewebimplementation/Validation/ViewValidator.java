package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import com.web.dictionaryservice.dictionaryservicewebimplementation.service.ViewService;
import org.springframework.http.HttpStatus;

import java.util.function.Predicate;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.invalid;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.valid;

public interface ViewValidator extends Validator<ViewService>{
    static ViewValidator holds(Predicate<ViewService> predicate, String message){
        return request -> predicate.test(request) ? valid() : invalid(message);
    }

    static ViewValidator userExists(long userId){
        return holds(request -> request.getUserRepository().existsById(userId), HttpStatus.NOT_FOUND.value() + " User not found");
    }
    static ViewValidator dictionaryExists(long dictionaryId){
        return holds(request -> request.getDictionaryRepository().existsById(dictionaryId), HttpStatus.NOT_FOUND.value() + " Dictionary not found");
    }
    static ViewValidator keyExists(long keyId){
        return holds(request -> request.getKeyRepository().existsById(keyId), HttpStatus.NOT_FOUND.value() + " Key not found");
    }

    default ViewValidator and(ViewValidator other) {
        return customer -> {
            final ValidationResult result = apply(customer);
            return result.getIsValid() ? other.apply(customer) : result;
        };
    }
}
