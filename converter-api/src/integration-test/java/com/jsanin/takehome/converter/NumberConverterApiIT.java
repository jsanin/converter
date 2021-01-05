package com.jsanin.takehome.converter;

import com.jsanin.takehome.converter.ConverterSpringBootTest;
import com.jsanin.takehome.converter.ConverterUrlMapping;
import com.jsanin.takehome.converter.NumberToWordsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ConverterSpringBootTest
public class NumberConverterApiIT {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testTransformNumberToWords_PositiveNumber_Success() {
        final int number = 123;
        final String expectedResult = "One hundred twenty three";
        ResponseEntity<NumberToWordsResponse> response =
                testRestTemplate.getForEntity(ConverterUrlMapping
                        .CONVERTER_BASE_PATH + "/numberToWords/{number}", NumberToWordsResponse.class, number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getError());
        assertEquals(expectedResult, response.getBody().getWords());
    }

    @Test
    public void testTransformNumberToWords_NegativeNumber_Success() {
        final int number = -123;
        final String expectedResult = "Minus one hundred twenty three";
        ResponseEntity<NumberToWordsResponse> response =
                testRestTemplate.getForEntity(ConverterUrlMapping
                        .CONVERTER_BASE_PATH + "/numberToWords/{number}", NumberToWordsResponse.class, number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getError());
        assertEquals(expectedResult, response.getBody().getWords());
    }

    @Test
    public void testTransformNumberToWords_PositiveLargeNumber_Success() {
        final int number = Integer.MAX_VALUE;
        final String expectedResult = "Two billion one hundred forty seven million four " +
                "hundred eighty three thousand six hundred forty seven"; // 2_147_483_647
        ResponseEntity<NumberToWordsResponse> response =
                testRestTemplate.getForEntity(ConverterUrlMapping
                        .CONVERTER_BASE_PATH + "/numberToWords/{number}", NumberToWordsResponse.class, number);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().getError());
        assertEquals(expectedResult, response.getBody().getWords());
    }

    @Test
    public void testTransformNumberToWords_InvalidFormat_Failure() {
        final String notANumber = "12NOT_A_NUMBER";
        ResponseEntity<NumberToWordsResponse> response =
                testRestTemplate.getForEntity(ConverterUrlMapping
                        .CONVERTER_BASE_PATH + "/numberToWords/{number}", NumberToWordsResponse.class, notANumber);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getError());
    }

    @Test
    public void testTransformNumberToWords_OutOfRange_Failure() {
        final long outOfRange = Integer.MAX_VALUE + 100L;
        ResponseEntity<NumberToWordsResponse> response =
                testRestTemplate.getForEntity(ConverterUrlMapping
                        .CONVERTER_BASE_PATH + "/numberToWords/{number}", NumberToWordsResponse.class, outOfRange);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getError());
    }

}
