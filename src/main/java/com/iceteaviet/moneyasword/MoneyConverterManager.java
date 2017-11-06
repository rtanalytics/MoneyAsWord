package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import java.math.BigDecimal;

import static com.google.common.base.Verify.verifyNotNull;

public class MoneyConverterManager extends ConverterManager<BigDecimal> {
    private final NumberToStringConverter<BigDecimal> converter;

    private MoneyConverterManager(NumberToStringConverter<BigDecimal> converter) {
        this.converter = converter;
    }

    public static MoneyConverterManager getConverterManager(int languageType) {
        return getConverterManager(languageType, null);
    }

    public static MoneyConverterManager getConverterManager(int languageType, String newCurrencySign) {
        return new MoneyConverterManager(getContainerFromLanguageType(languageType, newCurrencySign).getBankingMoneyConverter());
    }

    @Override
    public String asWords(BigDecimal value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
