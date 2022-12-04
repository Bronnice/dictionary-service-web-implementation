package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.service.WatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WatchingController {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    WatchingService watchingService;

    @GetMapping(path = "/{user_id}/dictionaries")
    public ResponseEntity<?> getAllDictionaries(@PathVariable(value = "user_id")User user){
        return watchingService.getAllDictionaries(user);
    }

    @GetMapping(path = "/{user_id}/dictionaries/{dictionary_id}")
    public ResponseEntity<?> getDictionaryById(@PathVariable(value = "user_id") User user, @PathVariable(value = "dictionary_id") Dictionary dictionary){
        return watchingService.getDictionaryById(user, dictionary);
    }

    @GetMapping(path = "/{user_id}/dictionaries/{dictionary_id}/{key_id}")
    public ResponseEntity<?> findValueByKey(@PathVariable(value = "user_id") User user,
                                        @PathVariable(value = "dictionary_id") Dictionary dictionary,
                                        @PathVariable(value = "key_id") Key key){
        return watchingService.findValueByKey(user, dictionary, key);
    }

    @GetMapping(path = "/{user_id}/dictionaries/{dictionary_id}/{key}/delete/{value_id}")
    public ResponseEntity<?> deleteValueByKey(@PathVariable(value = "user_id") User user,
                                            @PathVariable(value = "dictionary_id") Dictionary dictionary,
                                            @PathVariable(value = "key") Key key,
                                              @PathVariable(value = "value_id") Value value){
        return watchingService.deleteValueByKey(user, dictionary, key, value);
    }
}
