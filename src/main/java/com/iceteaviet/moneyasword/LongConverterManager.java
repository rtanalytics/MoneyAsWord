package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;

/**
 * Manage functions to convert number (long) to text
 */

public class LongConverterManager extends BaseConverterManager<Long> {
    private final NumberToWordsConverter<Long> converter;

    private LongConverterManager(NumberToWordsConverter<Long> converter) {
        this.converter = converter;
    }

    public static LongConverterManager getConverterManager(int languageType) {
        return new LongConverterManager(getContainerFromLanguageType(languageType).getLongConverter());
    }

    @Override
    public String asWords(Long value) {
        return converter.asWords(value);
    }
}