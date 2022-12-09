package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ViewController {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    ViewService viewService;

    @GetMapping(path = "/users/{user_id}/dictionaries")
    public ResponseEntity<?> getAllDictionaries(@PathVariable(value = "user_id")long user_id){
        return viewService.getAllDictionaries(user_id);
    }

    @GetMapping(path = "/users/{user_id}/dictionaries/{dictionary_id}")
    public ResponseEntity<?> getDictionaryById(@PathVariable(value = "user_id") long user_id, @PathVariable(value = "dictionary_id") long dictionary_id){
        return viewService.getDictionaryById(user_id, dictionary_id);
    }

    @GetMapping(path = "/users/{user_id}/dictionaries/{dictionary_id}/{key_id}")
    public ResponseEntity<?> findValueByKey(@PathVariable(value = "user_id") long userId,
                                            @PathVariable(value = "dictionary_id") long dictionaryId,
                                            @PathVariable(value = "key_id") long keyId){
        return viewService.findValueByKey(userId, dictionaryId, keyId);
    }
}
