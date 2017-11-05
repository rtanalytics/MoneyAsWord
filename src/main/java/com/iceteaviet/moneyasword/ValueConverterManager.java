package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import static com.google.common.base.Verify.verifyNotNull;
import static com.iceteaviet.moneyasword.internal.Container.*;

public enum ValueConverterManager {

    BRAZILIAN_PORTUGUESE_INTEGER(brazilianPortugueseContainer().getNumbersConverter()),
    GERMAN_INTEGER(germanContainer().getNumbersConverter()),
    POLISH_INTEGER(polishContainer().getNumbersConverter()),
    RUSSIAN_INTEGER(russianContainer().getNumbersConverter()),
    CZECH_INTEGER(czechContainer().getNumbersConverter()),
    ENGLISH_INTEGER(englishContainer().getNumbersConverter());

    private final NumberToStringConverter<Integer> converter;

    ValueConverterManager(NumberToStringConverter<Integer> converter) {
        this.converter = converter;
    }

    public String asWords(Integer value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
