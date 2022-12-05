package com.web.dictionaryservice.dictionaryservicewebimplementation.pojo;

import com.web.dictionaryservice.dictionaryservicewebimplementation.model.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeyRequest {
    private String key;
    private List<Value> values;
}
