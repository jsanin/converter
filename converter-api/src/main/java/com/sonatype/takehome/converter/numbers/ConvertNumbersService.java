package com.sonatype.takehome.converter.numbers;

public interface ConvertNumbersService {

    /**
     * Convert the given number to its equivalent in words
     * @param number
     * @return string with the word representation of the given number
     */
    String convertNumberToWords(long number);
}