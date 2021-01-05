package com.sonatype.takehome.converter;

public class NumberToWordsResponse {
    private final String error;
    private final String message;
    private final String words;

    public NumberToWordsResponse(String words, String error, String message) {
        this.words = words;
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getWords() {
        return words;
    }

    public String getMessage() {
        return message;
    }
}
