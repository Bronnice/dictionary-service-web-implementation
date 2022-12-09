package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.CreateValidator;
import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.KeyValidator;
import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
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

import java.util.Optional;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.CreateValidator.*;
import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.KeyValidator.*;

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

    public ResponseEntity<?> createDictionary(long userId, DictionaryRequest dictionaryRequest) {
        CreateValidator createValidator = userExists(userId);
        ValidationResult validationResult = createValidator.apply(this);

        if (!validationResult.getIsValid()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        Dictionary dictionary = new Dictionary(dictionaryRequest.getName(), userId, dictionaryRequest.getWordType(), dictionaryRequest.getKeyLength());
        dictionaryRepository.save(dictionary);

        return ResponseEntity.ok(new MessageResponse("Dictionary CREATED"));
    }


    public ResponseEntity<?> createKey(long userId, long dictionaryId, KeyRequest keyRequest) {
        CreateValidator createValidator = userExists(userId).and(
                dictionaryExists(dictionaryId)
        );
        ValidationResult validationResult = createValidator.apply(this);

        if (!validationResult.getIsValid()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        Key key = new Key(keyRequest.getKey());


        Optional<Dictionary> dictionary = dictionaryRepository.findById(dictionaryId);
        try {
            if (dictionary.isPresent()) {
                Dictionary dic = dictionary.get();
                KeyValidator keyValidator = keyIsNotNull().and(
                        keyIsNotEmpty().and(
                                keyLength(dic.getKeyLength()).and(
                                        keyWordType(dic.getWordType())))
                );
                ValidationResult validationResult2 = keyValidator.apply(keyRequest);
                if (!validationResult2.getIsValid()) {
                    return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult2.getErrorMessage()));
                }
                keyRepository.save(key);
                dic.getKeys().add(key);
                dictionaryRepository.save(dic);
            }

        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.BAD_REQUEST.value() + " Key is null"));
        }

        return ResponseEntity.ok(new MessageResponse("Key CREATED"));
    }

    public ResponseEntity<?> createValue(long userId, long dictionaryId, long keyId, ValueRequest valueRequest) {
        CreateValidator createValidator = userExists(userId).and(
                dictionaryExists(dictionaryId).and(
                        keyExists(keyId)
                )
        );
        ValidationResult validationResult = createValidator.apply(this);

        if (!validationResult.getIsValid()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        Value value = new Value(valueRequest.getValue());
        valueRepository.save(value);

        Optional<Key> key = keyRepository.findById(keyId);
        try {
            if (key.isPresent()) {
                Key tempKey = key.get();
                tempKey.getValues().add(value);
                keyRepository.save(tempKey);
            }

        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.BAD_REQUEST.value() + " Value is null"));
        }

        return ResponseEntity.ok(new MessageResponse("Value CREATED"));
    }
}
