package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private ValueRepository valueRepository;

    public ResponseEntity<?> getAllDictionaries(User user) {
        if (!userRepository.existsById(user.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dictionaryRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getDictionaryById(User user, Dictionary dictionary) {
        if (!userRepository.existsById(user.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionary.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dictionaryRepository.findById(dictionary.getId()), HttpStatus.OK);
    }

    public ResponseEntity<?> findValueByKey(User user, Dictionary dictionary, Key key){
        if (!userRepository.existsById(user.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionary.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);
        if(!keyRepository.existsByKey(key.getKey()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Key not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(key.getValues(), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteValueByKey(User user, Dictionary dictionary, Key key, Value value){
        if (!userRepository.existsById(user.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionary.getId()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);
        if(!keyRepository.existsByKey(key.getKey()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Key not found"), HttpStatus.NOT_FOUND);
        if(!valueRepository.existsByValue(value.getValue()))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Value not found"), HttpStatus.NOT_FOUND);
        valueRepository.deleteById(value.getId());
        return ResponseEntity.ok(new MessageResponse("Value deleted"));
    }
}
