package com.iceteaviet.moneyasword.internal.languages.german;

import com.iceteaviet.moneyasword.internal.GenderAwareIntegerToStringConverter;
import com.iceteaviet.moneyasword.internal.NumberProcessor;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import java.util.Map;

public class GermanIntegerToWordsConverter implements NumberToStringConverter<Integer> {

    private final NumberToStringConverter<Integer> bigNumbersConverter;
    private final Map<Integer, String> exceptions;
    private final GenderAwareIntegerToStringConverter smallNumbersConverter;

    public GermanIntegerToWordsConverter(NumberToStringConverter<Integer> bigNumbersConverter,
                                         Map<Integer, String> exceptions,
                                         GenderAwareIntegerToStringConverter smallNumbersConverter) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.exceptions = exceptions;
        this.smallNumbersConverter = smallNumbersConverter;
    }

    @Override
    public String asWords(Integer value) {
        if (exceptions.containsKey(value)) {
            return exceptions.get(value);
        }

        Integer bigNumber = value / 1000000;
        Integer smallNumber = value % 1000000;

        return new NumberProcessor(bigNumbersConverter, smallNumbersConverter).process(bigNumber, smallNumber);
    }

}
