package com.iceteaviet.moneyasword.internal.languages.czech;

import com.iceteaviet.moneyasword.internal.NumberProcessor;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import java.util.Map;

public class CzechIntegerToWordsConverter implements NumberToStringConverter<Integer> {

    private final NumberToStringConverter<Integer> bigNumbersConverter;
    private final NumberToStringConverter<Integer> smallNumbersConverter;
    private final Map<Integer, String> exceptions;

    public CzechIntegerToWordsConverter(NumberToStringConverter<Integer> bigNumbersConverter,
                                        NumberToStringConverter<Integer> smallNumbersConverter,
                                        Map<Integer, String> exceptions) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.smallNumbersConverter = smallNumbersConverter;
        this.exceptions = exceptions;
    }

    @Override
    public String asWords(Integer value) {
        if (exceptions.containsKey(value)) {
            return exceptions.get(value);
        }

        Integer bigNumber = value / 1000;
        Integer smallNumber = value % 1000;

        return new NumberProcessor(bigNumbersConverter, smallNumbersConverter).process(bigNumber, smallNumber);
    }
}
