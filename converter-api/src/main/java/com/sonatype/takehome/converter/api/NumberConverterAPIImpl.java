package com.sonatype.takehome.converter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberConverterAPIImpl implements NumberConverterAPI {

    @Override
    public ResponseEntity<NumberToWordsResponse> transformNumberToWords(long number) {
        return null;
    }
}
