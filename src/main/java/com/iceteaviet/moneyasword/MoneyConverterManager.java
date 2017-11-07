package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.core.converters.BDBankingMoneyToWordsConverter;

import java.math.BigDecimal;

import static com.google.common.base.Verify.verifyNotNull;

public class MoneyConverterManager extends ConverterManager<BigDecimal> {
    private final BDBankingMoneyToWordsConverter converter;

    private MoneyConverterManager(BDBankingMoneyToWordsConverter converter) {
        this.converter = converter;
    }

    public static MoneyConverterManager getConverterManager(int languageType) {
        return getConverterManager(languageType, null);
    }

    public static MoneyConverterManager getConverterManager(int languageType, String newCurrencySign) {
        return new MoneyConverterManager(getContainerFromLanguageType(languageType, newCurrencySign)
                .getBankingMoneyConverter());
    }

    @Override
    public String asWords(BigDecimal value) {
        return asWords(value, false);
    }

    public String asWords(BigDecimal value, boolean showCurrencySignFirst) {
        verifyNotNull(value);

        return converter.asWords(value, showCurrencySignFirst);
    }
}
