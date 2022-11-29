package dictionaryserivce;

public class ValidatorImpl implements dictionaryserivce.Validator {
    private final int wordCount;
    private final dictionaryserivce.WordType wordType;

    public ValidatorImpl(int wordCount, dictionaryserivce.WordType wordType) {
        this.wordCount = wordCount;
        this.wordType = wordType;
    }

    public int getWordCount() {
        return wordCount;
    }

    public dictionaryserivce.WordType getWordType() {
        return wordType;
    }
}
