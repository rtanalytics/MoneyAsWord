package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.internal.Container;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import static com.google.common.base.Verify.verifyNotNull;

public class ValueConverterManager implements ConverterManager<Integer> {
    private final NumberToStringConverter<Integer> converter;

    private ValueConverterManager(NumberToStringConverter<Integer> converter) {
        this.converter = converter;
    }

    public static ValueConverterManager getConverterManager(int languageType) {
        switch (languageType) {
            case ENGLISH:

                return new ValueConverterManager(Container.englishContainer().getNumbersConverter());
            case CZECH:
                return new ValueConverterManager(Container.czechContainer().getNumbersConverter());

            case GERMAN:
                return new ValueConverterManager(Container.germanContainer().getNumbersConverter());

            case POLISH:
                return new ValueConverterManager(Container.polishContainer().getNumbersConverter());

            case BRAZILIAN_PORTUGUESE:
                return new ValueConverterManager(Container.brazilianPortugueseContainer().getNumbersConverter());

            case RUSSIAN:
                return new ValueConverterManager(Container.russianContainer().getNumbersConverter());

            default:
                return new ValueConverterManager(Container.englishContainer().getNumbersConverter());
        }
    }

    @Override
    public String asWords(Integer value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
