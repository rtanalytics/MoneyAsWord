package com.iceteaviet.moneyasword.internal;

public class ToStringConverter {

    public static GenderAwareIntegerToStringConverter toGenderAwareInteger(final NumberToStringConverter<Integer> integerToStringConverter) {
        return (value, genderType) -> integerToStringConverter.asWords(value);
    }
}
