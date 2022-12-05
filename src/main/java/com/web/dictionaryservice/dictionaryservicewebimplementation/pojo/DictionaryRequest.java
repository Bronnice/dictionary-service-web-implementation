package com.web.dictionaryservice.dictionaryservicewebimplementation.pojo;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Key;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DictionaryRequest {
    private Long user_id;
    private List<Key> keys;
}
