package com.iceteaviet.moneyasword.core.support;

import com.iceteaviet.moneyasword.core.GenderAwareIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.NumberToWordsConverter;

public class ToStringConverter {
    public static GenderAwareIntegerToWordsMapper toGenderAwareInteger(final NumberToWordsConverter<Integer> integerToStringConverter) {
        return (value, genderType) -> integerToStringConverter.asWords(value);
    }
}
