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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;

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

    public ResponseEntity<?> getAllDictionaries(long userId) {

        if (!userRepository.existsById(userId))
            return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.NOT_FOUND.value() + " User not found"));

        List<Dictionary> tempDic = new ArrayList<>();
        for (Dictionary item : dictionaryRepository.findAll()) {
            if(item.getUser_id().equals(userId))
                tempDic.add(item);
        }

        return new ResponseEntity<>(tempDic, HttpStatus.OK);
    }

    public ResponseEntity<?> getDictionaryById(long userId, long dictionaryId) {
        if (!userRepository.existsById(userId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionaryId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dictionaryRepository.findById(dictionaryId), HttpStatus.OK);
    }

    public ResponseEntity<?> findValueByKey(long userId, long dictionaryId, long keyId){
        if (!userRepository.existsById(userId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        if (!dictionaryRepository.existsById(dictionaryId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Dictionary not found"), HttpStatus.NOT_FOUND);
        if(!keyRepository.existsById(keyId))
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Key not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(valueRepository.findById(keyId), HttpStatus.OK);
    }
}
