package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import com.web.dictionaryservice.dictionaryservicewebimplementation.service.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WatchingController {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @GetMapping
    public List<Dictionary> getAllDictionaries(){
        return dictionaryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDictionaryById(@PathVariable(value = "id") Dictionary dictionary){
        try{
            Dictionary newDictionary = dictionaryRepository.findById(String.valueOf(dictionary.getId())).orElseThrow();
            return new ResponseEntity<>(newDictionary, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Dictionary with id " + dictionary.getId() + " not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
}
