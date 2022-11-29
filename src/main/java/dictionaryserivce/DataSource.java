package dictionaryserivce;

import com.google.gson.*;
import java.io.*;
import java.util.HashMap;

public class DataSource {
    private HashMap<String, String> dictionaryMap = new HashMap<>();
    private final File file;
    private static final GsonBuilder builder = new GsonBuilder();
    private static final Gson gson = builder.create();
    private final Dictionary dictionary;

    public DataSource(File file, Dictionary dictionary) {
        this.file = file;
        this.dictionary = dictionary;
    }

    private boolean checkWordType(String string) {
        switch (dictionary.getValidator().getWordType()) {
            case EnglishWithNums -> {
                return string.matches("[a-zA-Z0-9]");
            }
            case EnglishNoNums -> {
                return string.matches("[a-zA-Z]");
            }
            case OnlyNums -> {
                return string.matches("[0-9]");
            }
            default -> {
                return false;
            }
        }
    }

    public void addIssue(String key, String value) {
        try (FileWriter fw = new FileWriter(file.getName())) {
            if (key.length() <= dictionary.getValidator().getWordCount()
                    && checkWordType(key)
                    && checkWordType(value)
                    && !dictionaryMap.containsKey(key)) {
                dictionaryMap.put(key, value);
                fw.write(gson.toJson(dictionaryMap));
            } else
                throw new UnsupportedOperationException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteIssue(String key) {
        try (FileWriter fw = new FileWriter(file.getName())) {
            if (dictionaryMap.containsKey(key)) {
                dictionaryMap.remove(key);
                fw.write(gson.toJson(dictionaryMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAllFromFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getName()));
            dictionaryMap = gson.fromJson(br, HashMap.class);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readCoupleFromFile(String key) {
        try {
            this.readAllFromFile();
            if (dictionaryMap.containsKey(key)) {
                HashMap<String, String> tempMap = new HashMap<>();
                tempMap.put(key, dictionaryMap.get(key));
                return gson.toJson(tempMap);
            }
            else
                throw new IOException();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public boolean findByKey(String key) {
        this.readAllFromFile();
        return dictionaryMap.containsKey(key);
    }
}
