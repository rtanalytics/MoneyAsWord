package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.internal.Container;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import java.math.BigDecimal;

import static com.google.common.base.Verify.verifyNotNull;

public class MoneyConverterManager implements ConverterManager<BigDecimal> {
    private final NumberToStringConverter<BigDecimal> converter;

    private MoneyConverterManager(NumberToStringConverter<BigDecimal> converter) {
        this.converter = converter;
    }

    public static MoneyConverterManager getConverterManager(int languageType) {
        switch (languageType) {
            case ENGLISH:
                return new MoneyConverterManager(Container.englishContainer().getBankingMoneyConverter());

            case CZECH:
                return new MoneyConverterManager(Container.czechContainer().getBankingMoneyConverter());

            case GERMAN:
                return new MoneyConverterManager(Container.germanContainer().getBankingMoneyConverter());

            case POLISH:
                return new MoneyConverterManager(Container.polishContainer().getBankingMoneyConverter());

            case BRAZILIAN_PORTUGUESE:
                return new MoneyConverterManager(Container.brazilianPortugueseContainer().getBankingMoneyConverter());

            case RUSSIAN:
                return new MoneyConverterManager(Container.russianContainer().getBankingMoneyConverter());

            default:
                return new MoneyConverterManager(Container.englishContainer().getBankingMoneyConverter());
        }
    }

    @Override
    public String asWords(BigDecimal value) {
        verifyNotNull(value);

        return converter.asWords(value);
    }
}
