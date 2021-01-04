package com.sonatype.takehome.converter.numbers;

import com.sonatype.takehome.converter.numbers.impl.ConvertNumbersServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertNumbersServiceTest {

    private ConvertNumbersService convertNumbersService = new ConvertNumbersServiceImpl();

    @ParameterizedTest
    @MethodSource("provideConvertNumberToWords")
    void convertNumberToWords(long number, String words) {
        assertEquals(words, convertNumbersService.convertNumberToWords(number));
    }

    private static Stream<Arguments> provideConvertNumberToWords() {
        return Stream.of(
            Arguments.of(81155L, "eighty one thousand one hundred fifty five"),
            Arguments.of(381716L, "three hundred eighty one thousand seven hundred sixteen"),
            Arguments.of(381706007L, "three hundred eighty one million seven hundred six thousand seven"),
            Arguments.of(1000004000L, "one billion four thousand"),
            Arguments.of(46L, "forty six"),
            Arguments.of(0L, "Zero"),
            Arguments.of(13L, "Thirteen"),
            Arguments.of(85L, "Eighty five"),
            Arguments.of(5237L, "Five thousand two hundred and thirty seven"),
            Arguments.of(3450600004000L, "three thousand four hundred fifty  billion six hundred  million four thousand")
        );
    }

}