package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;

import static com.google.common.base.Verify.verifyNotNull;

public class LongConverterManager extends ConverterManager<Long> {
    private final NumberToWordsConverter<Long> converter;

    private LongConverterManager(NumberToWordsConverter<Long> converter) {
        this.converter = converter;
    }

    public static LongConverterManager getConverterManager(int languageType) {
        return new LongConverterManager(getContainerFromLanguageType(languageType).getLongConverter());
    }

    @Override
    public String asWords(Long value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
