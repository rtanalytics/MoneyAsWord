package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;

import static com.google.common.base.Verify.verifyNotNull;

public class IntegerConverterManager extends ConverterManager<Integer> {
    private final NumberToWordsConverter<Integer> converter;

    private IntegerConverterManager(NumberToWordsConverter<Integer> converter) {
        this.converter = converter;
    }

    public static IntegerConverterManager getConverterManager(int languageType) {
        return new IntegerConverterManager(getContainerFromLanguageType(languageType).getIntegerConverter());
    }

    @Override
    public String asWords(Integer value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
