package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.CreateValidator;
import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult;
import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ViewValidator;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.*;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.KeyRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.UserRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.ValueRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.CreateValidator.*;

@Service
@Getter
public class CreateService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    KeyRepository keyRepository;
    @Autowired
    ValueRepository valueRepository;

    public ResponseEntity<?> createDictionary(long userId, DictionaryRequest dictionaryRequest){
        CreateValidator createValidator = userExists(userId);
        ValidationResult validationResult = createValidator.apply(this);

        if(!validationResult.getIsValid()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        Dictionary dictionary = new Dictionary(dictionaryRequest.getName(), userId);
        dictionaryRepository.save(dictionary);

        return ResponseEntity.ok(new MessageResponse("Dictionary CREATED"));
    }


    public ResponseEntity<?> createKey(long userId, long dictionaryId, KeyRequest keyRequest) {
        CreateValidator createValidator = userExists(userId).and(
                dictionaryExists(dictionaryId)
        );
        ValidationResult validationResult = createValidator.apply(this);

        if(!validationResult.getIsValid()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        Key key = new Key(keyRequest.getKey());
        keyRepository.save(key);
        dictionaryRepository.findById(dictionaryId).get().getKeys().add(key);
        dictionaryRepository.save(dictionaryRepository.findById(dictionaryId).get());

        return ResponseEntity.ok(new MessageResponse("Key CREATED"));
    }

    public ResponseEntity<?> createValue(long userId, long dictionaryId, long keyId, ValueRequest valueRequest) {
        CreateValidator createValidator = userExists(userId).and(
                dictionaryExists(dictionaryId).and(
                       keyExists(keyId)
                )
        );
        ValidationResult validationResult = createValidator.apply(this);

        if(!validationResult.getIsValid()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        Value value = new Value(valueRequest.getValue());
        valueRepository.save(value);
        keyRepository.findById(keyId).get().getValues().add(value);
        keyRepository.save(keyRepository.findById(keyId).get());

        return ResponseEntity.ok(new MessageResponse("Value CREATED"));
    }
}
