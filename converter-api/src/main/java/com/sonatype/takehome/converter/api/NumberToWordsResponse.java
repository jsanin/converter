package com.sonatype.takehome.converter.api;

public class NumberToWordsResponse {
    private ErrorWrapper error;
    private String words;

    public ErrorWrapper getError() {
        return error;
    }

    public void setError(ErrorWrapper error) {
        this.error = error;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
