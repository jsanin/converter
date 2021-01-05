package com.jsanin.takehome.converter.impl;

import com.jsanin.takehome.converter.NumberConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class NumberConversionServiceImpl implements NumberConversionService {

    private static final Logger logger = LoggerFactory.getLogger(NumberConversionServiceImpl.class);

    private static final String ZERO = "Zero";
    private static final String MINUS = "Minus";
    private static final List<Integer> divisors;
    private static final List<String> numbersWords;
    private static final Map<Integer, String> tensWords;
    private static final Map<Integer, String> onesWords;

    static {
        divisors = new LinkedList<>();
        divisors.add(1_000_000_000);
        divisors.add(1_000_000);
        divisors.add(1_000);
        divisors.add(100);

        numbersWords = new LinkedList<>();
        numbersWords.add("billion");
        numbersWords.add("million");
        numbersWords.add("thousand");
        numbersWords.add("hundred");

        tensWords = new HashMap<>();
        tensWords.put(9, "ninety");
        tensWords.put(8, "eighty");
        tensWords.put(7, "seventy");
        tensWords.put(6, "sixty");
        tensWords.put(5, "fifty");
        tensWords.put(4, "forty");
        tensWords.put(3, "thirty");
        tensWords.put(2, "twenty");

        onesWords = new HashMap<>();
        onesWords.put(0, "");
        onesWords.put(1, "one");
        onesWords.put(2, "two");
        onesWords.put(3, "three");
        onesWords.put(4, "four");
        onesWords.put(5, "five");
        onesWords.put(6, "six");
        onesWords.put(7, "seven");
        onesWords.put(8, "eight");
        onesWords.put(9, "nine");
        onesWords.put(10, "ten");
        onesWords.put(11, "eleven");
        onesWords.put(12, "twelve");
        onesWords.put(13, "thirteen");
        onesWords.put(14, "fourteen");
        onesWords.put(15, "fifteen");
        onesWords.put(16, "sixteen");
        onesWords.put(17, "seventeen");
        onesWords.put(18, "eighteen");
        onesWords.put(19, "nineteen");
    }

    private StringBuilder getOnesAndTensNumber(int between1and99) {
        StringBuilder sb = new StringBuilder();
        if(0 < between1and99 && between1and99 < 20) {
            sb.append(onesWords.get(between1and99));
        } else if(100 > between1and99 && between1and99 > 19) {
            sb.append(tensWords.get(between1and99 / 10)).append(" ");
            sb.append(onesWords.get(between1and99 % 10));
        }
        return sb;
    }

    private StringBuilder convertNumber(long number) {
        if(number < 100) {
            return getOnesAndTensNumber(Long.valueOf(number).intValue());
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Integer divisor : divisors) {
            long result = number / divisor;
            if(result <= 0) {
                // try next
                i++;
                continue;
            }
            sb.append(convertNumber(result)).append(" ");
            sb.append(numbersWords.get(i)).append(" ");
            sb.append(convertNumber(number % divisor));
            break;
        }
        return sb;
    }

    @Override
    public String convertNumberToWords(int number) {
        if(number == 0) {
            return ZERO;
        }
        long toConvert = number;
        StringBuilder converted = new StringBuilder();
        if(number < 0) {
            toConvert = number * -1L;
            converted.append(MINUS).append(" ");
        }
        converted.append(convertNumber(toConvert));
        return converted.substring(0,1).toUpperCase() + converted.substring(1).toLowerCase().stripTrailing();
    }

    @Override
    public String convertNumberToWords(String strNumber) {
        try {
            return convertNumberToWords(Integer.valueOf(strNumber));
        } catch(RuntimeException e) {
            logger.error("Error converting number {}", strNumber, e);
            throw e;
        }
    }
}
