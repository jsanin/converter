package com.jsanin.takehome.converter.impl;

import com.jsanin.takehome.converter.NumberConversionService;
import com.jsanin.takehome.converter.NumberConverterApi;
import com.jsanin.takehome.converter.NumberToWordsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberConverterApiImpl implements NumberConverterApi {

    private static final String INVALID_FORMAT_ERROR = "Invalid format";
    private static final String INVALID_FORMAT_ERROR_DESCRIPTION =
            "It should be a valid integer number within the range " + Integer.MAX_VALUE + " and " + Integer.MIN_VALUE +
            ". Negative numbers are supported.";

    private static final String UNEXPECTED_ERROR = "Unexpected error";

    private NumberConversionService convertNumbersService;

    public NumberConverterApiImpl(NumberConversionService convertNumbersService) {
        this.convertNumbersService = convertNumbersService;
    }


    @Override
    public ResponseEntity<NumberToWordsResponse> transformNumberToWords(String strNumber) {
        ResponseEntity<NumberToWordsResponse> toReturn = null;
        try {
            toReturn = ResponseEntity.ok(
                    new NumberToWordsResponse(convertNumbersService.convertIntegerToWords(strNumber), null, null));

        } catch(NumberFormatException e) {
            toReturn = ResponseEntity.badRequest().
                    body(new NumberToWordsResponse(null, INVALID_FORMAT_ERROR, INVALID_FORMAT_ERROR_DESCRIPTION));
        } catch(Exception e) {
            toReturn = ResponseEntity.badRequest().
                    body(new NumberToWordsResponse(null, UNEXPECTED_ERROR, UNEXPECTED_ERROR));
        }
        return toReturn;
    }
}
