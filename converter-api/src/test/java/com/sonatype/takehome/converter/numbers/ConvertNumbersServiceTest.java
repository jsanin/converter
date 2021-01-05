package com.sonatype.takehome.converter.numbers;

import com.sonatype.takehome.converter.numbers.impl.ConvertNumbersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertNumbersServiceTest {

    private ConvertNumbersService convertNumbersService = new ConvertNumbersServiceImpl();

    @ParameterizedTest
    @MethodSource("provideConvertNumberToWords")
    void convertNumberToWords(int number, String words) {
        assertEquals(words, convertNumbersService.convertNumberToWords(number));
    }

    private static Stream<Arguments> provideConvertNumberToWords() {
        return Stream.of(
            Arguments.of(81155, "Eighty one thousand one hundred fifty five"),
            Arguments.of(381716, "Three hundred eighty one thousand seven hundred sixteen"),
            Arguments.of(381706007, "Three hundred eighty one million seven hundred six thousand seven"),
            Arguments.of(1000004000, "One billion four thousand"),
            Arguments.of(46, "Forty six"),
            Arguments.of(-46, "Minus forty six"),
            Arguments.of(0, "Zero"),
            Arguments.of(1, "One"),
            Arguments.of(13, "Thirteen"),
            Arguments.of(85, "Eighty five"),
            Arguments.of(5237, "Five thousand two hundred and thirty seven"),
            Arguments.of(-5237, "Minus five thousand two hundred and thirty seven"),
            Arguments.of(Integer.MAX_VALUE, "Two billion one hundred forty seven million four hundred eighty three thousand six hundred forty seven"), // 2_147_483_647
            Arguments.of(Integer.MIN_VALUE, "Minus two billion one hundred forty seven million four hundred eighty three thousand six hundred forty eight") // -2_147_483_648
            //Arguments.of(3450600004000, "three thousand four hundred fifty  billion six hundred  million four thousand")
        );
    }

    @Test
    public void testConvertInString() {
        // greater than max value
        convertNumbersService.convertNumberToWords("-2147483648");

    }

}