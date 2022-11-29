package dictionaryserivce;

import java.io.*;

public class Dictionary{

    public DataSource dataSource;
    private final dictionaryserivce.Validator validator;

    public Dictionary(String path, String fileName, dictionaryserivce.Validator validator){
        final File file = new File(path, (fileName + ".json"));
        this.validator = validator;
        this.dataSource = new DataSource(file, this);
    }

    public dictionaryserivce.Validator getValidator() {
        return validator;
    }
}