package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
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

    @GetMapping(path = "/{user_id}/dictionaries")
    public ResponseEntity<?> getAllDictionaries(@PathVariable(value = "user_id")long user_id){
        return viewService.getAllDictionaries(user_id);
    }

    @GetMapping(path = "/{user_id}/dictionaries/{dictionary_id}")
    public ResponseEntity<?> getDictionaryById(@PathVariable(value = "user_id") long user_id, @PathVariable(value = "dictionary_id") long dictionary_id){
        return viewService.getDictionaryById(user_id, dictionary_id);
    }

    @GetMapping(path = "/{user_id}/dictionaries/{dictionary_id}/{key_id}")
    public ResponseEntity<?> findValueByKey(@PathVariable(value = "user_id") long userId,
                                            @PathVariable(value = "dictionary_id") long dictionaryId,
                                            @PathVariable(value = "key") long keyId){
        return viewService.findValueByKey(userId, dictionaryId, keyId);
    }

//    @PostMapping(path = "/{user_id}/dictionaries/{dictionary_id}/{key}/delete/{value_id}")
//    public ResponseEntity<?> deleteValueByKey(@PathVariable(value = "user_id") long user_id,
//                                            @PathVariable(value = "dictionary_id") long dictionary_id,
//                                            @PathVariable(value = "key") String key,
//                                              @PathVariable(value = "value_id") Value value){
//        return viewService.deleteValueByKey(user_id, dictionary_id, key, value);
//    }
}
