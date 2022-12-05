package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.*;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.KeyRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.UserRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

///TODO Sane validation
@Service
public class CreateService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    KeyRepository keyRepository;
    @Autowired
    ValueRepository valueRepository;

    public ResponseEntity<?> createDictionary(User user, DictionaryRequest dictionaryRequest){
        if (!userRepository.existsById(user.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);

        Dictionary dictionary = new Dictionary(dictionaryRequest.getName(), user.getId());
        dictionaryRepository.save(dictionary);

        return ResponseEntity.ok(new MessageResponse("Dictionary CREATED"));
    }


    public ResponseEntity<?> createKey(long userId, long dictionaryId, KeyRequest keyRequest) {
        if (!userRepository.existsById(userId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionaryId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);

        Key key = new Key(keyRequest.getKey());
        keyRepository.save(key);
        dictionaryRepository.findById(dictionaryId).get().getKeys().add(key);
        dictionaryRepository.save(dictionaryRepository.findById(dictionaryId).get());

        return ResponseEntity.ok(new MessageResponse("Key CREATED"));
    }

    public ResponseEntity<?> createValue(long userId, long dictionaryId, long keyId, ValueRequest valueRequest) {
        if (!userRepository.existsById(userId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionaryId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);
        if(!keyRepository.existsById(keyId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Key not found"), HttpStatus.NOT_FOUND);

        Value value = new Value(valueRequest.getValue());
        valueRepository.save(value);
        keyRepository.findById(keyId).get().getValues().add(value);
        keyRepository.save(keyRepository.findById(keyId).get());

        return ResponseEntity.ok(new MessageResponse("Value CREATED"));
    }
}
