package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ValidationResult;
import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ViewValidator;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.AppError;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.MessageResponse;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.KeyRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.UserRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.ValueRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.ViewValidator.*;
import static org.springframework.http.ResponseEntity.badRequest;

@Service
@Getter
public class ViewService {

    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private ValueRepository valueRepository;

    public ResponseEntity<?> getAllDictionaries(long userId) {

        ViewValidator viewValidator = userExists(userId);
        ValidationResult validationResult = viewValidator.apply(this);

        if(!validationResult.getIsValid()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        List<Dictionary> tempDic = new ArrayList<>();
        for (Dictionary item : dictionaryRepository.findAll()) {
            if(item.getUser_id().equals(userId))
                tempDic.add(item);
        }

        return new ResponseEntity<>(tempDic, HttpStatus.OK);
    }

    public ResponseEntity<?> getDictionaryById(long userId, long dictionaryId) {
        ViewValidator viewValidator = userExists(userId).and(dictionaryExists(dictionaryId));
        ValidationResult validationResult = viewValidator.apply(this);

        if(!validationResult.getIsValid()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }
        return new ResponseEntity<>(dictionaryRepository.findById(dictionaryId), HttpStatus.OK);
    }

    public ResponseEntity<?> findValueByKey(long userId, long dictionaryId, long keyId){
        ViewValidator viewValidator = userExists(userId).and(
                dictionaryExists(dictionaryId).and(
                        keyExists(keyId)));
        ValidationResult validationResult = viewValidator.apply(this);

        if(!validationResult.getIsValid()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + validationResult.getErrorMessage()));
        }

        return new ResponseEntity<>(keyRepository.findById(keyId), HttpStatus.OK);
    }
}
