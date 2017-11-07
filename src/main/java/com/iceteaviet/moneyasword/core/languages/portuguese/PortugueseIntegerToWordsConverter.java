package com.iceteaviet.moneyasword.core.languages.portuguese;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.MultiFormNumber;
import com.iceteaviet.moneyasword.core.support.NumberProcessor;

import java.util.Map;

public class PortugueseIntegerToWordsConverter implements NumberToWordsConverter<Integer> {
    private final Map<Integer, MultiFormNumber> exceptions;
    private final NumberProcessor numberProcessor;

    public PortugueseIntegerToWordsConverter(NumberProcessor numberProcessor, Map<Integer, MultiFormNumber> exceptions) {
        this.exceptions = exceptions;
        this.numberProcessor = numberProcessor;
    }

    @Override
    public String asWords(Integer value) {
        if (exceptions.containsKey(value)) {
            return exceptions.get(value).getAloneForm();
        }

        Integer bigNumber = value / 1000000;
        Integer smallNumber = value % 1000000;

        return numberProcessor.process(bigNumber, smallNumber);
    }
}
