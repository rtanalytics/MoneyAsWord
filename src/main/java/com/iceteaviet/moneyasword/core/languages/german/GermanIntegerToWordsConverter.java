package com.iceteaviet.moneyasword.core.languages.german;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.support.NumberProcessor;

import java.util.Map;

public class GermanIntegerToWordsConverter implements NumberToWordsConverter<Integer> {
    private final Map<Integer, String> exceptions;
    private final NumberProcessor numberProcessor;

    public GermanIntegerToWordsConverter(NumberProcessor numberProcessor, Map<Integer, String> exceptions) {
        this.exceptions = exceptions;
        this.numberProcessor = numberProcessor;
    }

    @Override
    public String asWords(Integer value) {
        if (exceptions.containsKey(value)) {
            return exceptions.get(value);
        }

        Integer bigNumber = value / 1000000;
        Integer smallNumber = value % 1000000;

        return numberProcessor.process(bigNumber, smallNumber);
    }
}
