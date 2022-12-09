package com.web.dictionaryservice.dictionaryservicewebimplementation.pojo;

import com.web.dictionaryservice.dictionaryservicewebimplementation.Validation.WordType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryRequest {
    private String name;
    private int keyLength;
    private WordType wordType;
}
