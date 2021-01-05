package com.jsanin.takehome.converter;

import com.jsanin.takehome.converter.impl.NumberConversionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberConversionServiceTest {

    private NumberConversionService numberConversionService = new NumberConversionServiceImpl();

    @ParameterizedTest
    @MethodSource("provideConvertNumberToWords")
    void tesConvertNumberToWords_Integer(int number, String words) {
        assertEquals(words, numberConversionService.convertNumberToWords(number));
    }

    private static Stream<Arguments> provideConvertNumberToWords() {
        return Stream.of(
            Arguments.of(81155, "Eighty one thousand one hundred and fifty five"),
            Arguments.of(381716, "Three hundred eighty one thousand seven hundred and sixteen"),
            Arguments.of(381706007, "Three hundred eighty one million seven hundred six thousand and seven"),
            Arguments.of(1000004000, "One billion four thousand"),
            Arguments.of(345900, "Three hundred forty five thousand nine hundred"),
            Arguments.of(46, "Forty six"),
            Arguments.of(-46, "Minus forty six"),
            Arguments.of(0, "Zero"),
            Arguments.of(-0, "Zero"),
            Arguments.of(1, "One"),
            Arguments.of(13, "Thirteen"),
            Arguments.of(85, "Eighty five"),
            Arguments.of(5237, "Five thousand two hundred and thirty seven"),
            Arguments.of(-5237, "Minus five thousand two hundred and thirty seven"),
            Arguments.of(2_000_000_001, "Two billion and one"),
            Arguments.of(Integer.MAX_VALUE, "Two billion one hundred forty seven million four " +
                    "hundred eighty three thousand six hundred and forty seven"), // 2_147_483_647
            Arguments.of(Integer.MIN_VALUE, "Minus two billion one hundred forty seven million four " +
                    "hundred eighty three thousand six hundred and forty eight") // -2_147_483_648
        );
    }

    @Test
    public void tesConvertNumberToWords_NotInLowerRange() {
        assertThrows(NumberFormatException.class, () -> {
            // less than min value
            // min allowed -2_147_483_648 (Integer.MIN_VALUE)
            numberConversionService.convertIntegerToWords("-2147483649");
        });
    }

    @Test
    public void tesConvertNumberToWords_NotInMaxRange() {
        assertThrows(NumberFormatException.class, () -> {
            // greater than max value
            // max allowed 2_147_483_647 (Integer.MAX_VALUE)
            numberConversionService.convertIntegerToWords("2147483648");
        });
    }

    @Test
    public void tesConvertNumberToWords_NotANumber() {
        assertThrows(NumberFormatException.class, () -> {
            // not a number
            numberConversionService.convertIntegerToWords("       ");
        });
    }

    @Test
    public void tesConvertNumberToWords_NotAnInteger() {
        assertThrows(NumberFormatException.class, () -> {
            // not a integer
            numberConversionService.convertIntegerToWords("90.87");
        });
    }

    @Test
    public void tesConvertNumberToWords_NotAnInteger_WithComma() {
        assertThrows(NumberFormatException.class, () -> {
            // not a integer
            numberConversionService.convertIntegerToWords("90,87");
        });
    }

    @Test
    public void tesConvertNumberToWords_NotValidNegativeNumber() {
        assertThrows(NumberFormatException.class, () -> {
            // not a valid negative number
            numberConversionService.convertIntegerToWords("- 90");
        });
    }

    @Test
    public void tesConvertNumberToWords_NullValue() {
        assertThrows(NumberFormatException.class, () -> {
            // not a valid negative number
            numberConversionService.convertIntegerToWords(null);
        });
    }

    @ParameterizedTest
    @MethodSource("provideConvertNumberToWords_Strings")
    void tesConvertNumberToWords_String(String number, String words) {
        assertEquals(words, numberConversionService.convertIntegerToWords(number));
    }

    private static Stream<Arguments> provideConvertNumberToWords_Strings() {
        return Stream.of(
                Arguments.of("81155", "Eighty one thousand one hundred and fifty five"),
                Arguments.of("381716", "Three hundred eighty one thousand seven hundred and sixteen"),
                Arguments.of("-46", "Minus forty six"),
                Arguments.of("0", "Zero"),
                Arguments.of("0000000", "Zero"),
                Arguments.of("1", "One"),
                Arguments.of("000001", "One"),
                Arguments.of("-5237", "Minus five thousand two hundred and thirty seven"),
                Arguments.of("-0000000005237", "Minus five thousand two hundred and thirty seven")
        );
    }


}