package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.DictionaryRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.KeyRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.ValueRequest;
import com.web.dictionaryservice.dictionaryservicewebimplementation.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/create")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateController {

    @Autowired
    CreateService createService;

    @PostMapping(path = "/{user_id}/dictionaries/")
    public ResponseEntity<?> createDictionary(@PathVariable(value = "user_id") long userId, @RequestBody DictionaryRequest dictionaryRequest){
        return createService.createDictionary(userId, dictionaryRequest);
    }

    @PostMapping(path = "/{user_id}/dictionaries/{dictionary_id}")
    public ResponseEntity<?> createKey(@PathVariable(value = "user_id") long userId,
                                       @PathVariable(value = "dictionary_id") long dictionaryId,
                                       @RequestBody KeyRequest keyRequest){
        return createService.createKey(userId, dictionaryId, keyRequest);
    }

    @PostMapping(path = "/{user_id}/dictionaries/{dictionary_id}/{key_id}")
    public ResponseEntity<?> createValue(@PathVariable(value = "user_id") long userId,
                                         @PathVariable(value = "dictionary_id") long dictionaryId,
                                         @PathVariable(value = "key_id") long keyId,
                                         @RequestBody ValueRequest valueRequest){
        return createService.createValue(userId, dictionaryId, keyId, valueRequest);
    }
}
