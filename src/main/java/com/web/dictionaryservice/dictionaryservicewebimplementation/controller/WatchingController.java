package com.web.dictionaryservice.dictionaryservicewebimplementation.controller;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Dictionary;
import com.web.dictionaryservice.dictionaryservicewebimplementation.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.List;

@RestController
@RequestMapping("/watch")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WatchingController {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @GetMapping
    public List<Dictionary> getAllDictionaries(){
        return dictionaryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Dictionary getDictionaryById(@PathVariable(value = "id") Dictionary dictionary) throws Exception {
        if(!dictionaryRepository.existsById(dictionary.getId()))
            return dictionary;
        else
            throw new BindException("Искомый ключ не найден");
    }
}
