package com.web.dictionaryservice.dictionaryservicewebimplementation.service;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.DictionaryRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.UserRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EditService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DictionaryRepository dictionaryRepository;

    public ResponseEntity<?> createDictionary(User user, Dictionary dictionary, DictionaryRequest dictionaryRequest){
        throw new NotImplementedException();
    }
}
