package com.web.dictionaryservice.dictionaryservicewebimplementation.Validation;

import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.SignupRequest;
import org.springframework.http.HttpStatus;

import java.util.function.Predicate;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.invalid;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult.valid;

public interface SignUpValidator extends Validator<SignupRequest>{

    static SignUpValidator holds(Predicate<SignupRequest> predicate, String message){
        return request -> predicate.test(request) ? valid() : invalid(message);
    }

    //Username validation
    static SignUpValidator usernameIsNotEmpty() {
        return holds(request -> !request.getUsername().trim().isEmpty(),
                HttpStatus.BAD_REQUEST.value() + " Username is empty");
    }

    static SignUpValidator usernameLength() {
        return holds(request -> request.getUsername().length() < 16, HttpStatus.BAD_REQUEST.value() + " Username is too long");
    }

    static SignUpValidator usernameIsNotNull() {
        return holds(request -> request.getUsername() != null, HttpStatus.BAD_REQUEST.value() + " Username is null");
    }


    //Email validation
    static SignUpValidator emailContainsAtSign() {
        return holds(request -> request.getEmail().contains("@"), HttpStatus.BAD_REQUEST.value() + " Missing @-sign in email");
    }

    static SignUpValidator emailLength() {
        return holds(request -> request.getEmail().trim().length() < 50, HttpStatus.BAD_REQUEST.value() + " Email is too long");
    }

    static SignUpValidator emailIsNotEmpty() {
        return holds(request -> !request.getEmail().trim().isEmpty(), HttpStatus.BAD_REQUEST.value() + " Email is empty");
    }

    static SignUpValidator emailIsNotNull() {
        return holds(request -> request.getEmail() != null, HttpStatus.BAD_REQUEST.value() + " Email is null");
    }

    //Password validation
    static SignUpValidator passwordIsNotNull() {
        return holds(request -> request.getPassword() != null, HttpStatus.BAD_REQUEST.value() + " Password is null");
    }

    static SignUpValidator passwordIsNotEmpty() {
        return holds(request -> !request.getPassword().trim().isEmpty(), HttpStatus.BAD_REQUEST.value() + " Password is empty");
    }

    default SignUpValidator and(SignUpValidator other) {
        return customer -> {
            final ValidationResult result = apply(customer);
            return result.getIsValid() ? other.apply(customer) : result;
        };
    }
}