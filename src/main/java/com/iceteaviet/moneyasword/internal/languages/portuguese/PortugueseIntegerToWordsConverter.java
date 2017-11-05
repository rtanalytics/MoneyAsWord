package com.iceteaviet.moneyasword.internal.languages.portuguese;

import com.iceteaviet.moneyasword.internal.MultiFormNumber;
import com.iceteaviet.moneyasword.internal.NumberProcessor;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import java.util.Map;

public class PortugueseIntegerToWordsConverter implements NumberToStringConverter<Integer> {

    private final NumberToStringConverter<Integer> bigNumbersConverter;
    private final Map<Integer, MultiFormNumber> exceptions;
    private final NumberToStringConverter<Integer> smallNumbersConverter;

    public PortugueseIntegerToWordsConverter(NumberToStringConverter<Integer> bigNumbersConverter,
                                         Map<Integer, MultiFormNumber> exceptions,
                                             NumberToStringConverter<Integer> smallNumbersConverter) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.exceptions = exceptions;
        this.smallNumbersConverter = smallNumbersConverter;
    }

    @Override
    public String asWords(Integer value) {
        if (exceptions.containsKey(value)) {
            return exceptions.get(value).getAloneForm();
        }

        Integer bigNumber = value / 1000000;
        Integer smallNumber = value % 1000000;

        return new NumberProcessor(bigNumbersConverter, smallNumbersConverter).process(bigNumber, smallNumber);
    }
}
