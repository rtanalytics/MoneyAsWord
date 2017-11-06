package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import static com.google.common.base.Verify.verifyNotNull;

public class ValueConverterManager extends ConverterManager<Integer> {
    private final NumberToStringConverter<Integer> converter;

    private ValueConverterManager(NumberToStringConverter<Integer> converter) {
        this.converter = converter;
    }

    public static ValueConverterManager getConverterManager(int languageType) {
        return new ValueConverterManager(getContainerFromLanguageType(languageType).getIntegerConverter());
    }

    @Override
    public String asWords(Integer value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
