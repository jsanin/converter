package com.sonatype.takehome.converter.api;

import com.sonatype.takehome.converter.ConverterSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ConverterSpringBootTest
public class NumberConverterApiIT {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testTransformNumberToWords_Success() {
        final long number = 123L;
        final String expectedResult = "One hundred twenty three";
        ResponseEntity<NumberToWordsResponse> response =
                testRestTemplate.getForEntity(ConverterUrlMapping
                        .CONVERTER_BASE_PATH + "/numberToWords/{number}", NumberToWordsResponse.class, number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getError().isError());
        assertEquals(expectedResult, response.getBody().getWords());
    }

}
