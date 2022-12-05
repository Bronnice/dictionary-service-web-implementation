package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.model.User;
import com.web.dictionaryservice.dictionaryservicewebimplementation.pojo.DictionaryRequest;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/edit")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EditContoller {


    @PostMapping(path = "/{user_id}/dictionaries/{dictionary_id}")
    public ResponseEntity<?> createDictionary(@PathVariable(value = "user_id") User user, @PathVariable(value = "dictionary_id") Dictionary dictionary,
            @RequestBody DictionaryRequest dictionaryRequest){
        throw new NotImplementedException();
    }
}
