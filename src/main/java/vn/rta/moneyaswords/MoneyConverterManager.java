package vn.rta.moneyaswords;

import vn.rta.moneyaswords.core.converters.BDBankingMoneyToWordsConverter;

import java.math.BigDecimal;

/**
 * Manage functions to convert an amount of money to text
 */

public class MoneyConverterManager extends BaseConverterManager<BigDecimal> {
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
        return converter.asWords(value, showCurrencySignFirst);
    }
}
