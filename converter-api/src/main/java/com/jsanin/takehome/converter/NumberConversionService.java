package com.jsanin.takehome.converter;

public interface NumberConversionService {

    /**
     * Convert the given number to its equivalent in words
     * @param number
     * @return string with the word representation of the given number
     */
    String convertNumberToWords(int number);

    /**
     * Convert the given string representation of a valid java Integer to its equivalent in words.
     * If <code>strNumber</code> is an invalid Integer then this throws a {@link NumberFormatException}
     * @param strNumber
     * @return string with the word representation of the given number
     * @throws NumberFormatException if the given string is not a valid Integer
     */
    String convertIntegerToWords(String strNumber);
}
