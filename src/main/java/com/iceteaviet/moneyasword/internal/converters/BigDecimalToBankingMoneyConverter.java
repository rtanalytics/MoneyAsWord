package com.iceteaviet.moneyasword.internal.converters;

import com.iceteaviet.moneyasword.internal.NumberToStringConverter;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

public class BigDecimalToBankingMoneyConverter implements NumberToStringConverter<BigDecimal> {

    private static final String FORMAT_FULL = "%s %s %02d/100";
    private static final String FORMAT_NO_SUBUNIT = "%s %s";
    private static final int MAXIMAL_DECIMAL_PLACES_COUNT = 2;
    private final NumberToStringConverter<Integer> converter;
    private final String currencySymbol;

    public BigDecimalToBankingMoneyConverter(NumberToStringConverter<Integer> converter, String currencySymbol) {
        this.converter = converter;
        this.currencySymbol = currencySymbol;
    }

    @Override
    public String asWords(BigDecimal value) {
        return asWords(value, false);
    }

    public String asWords(BigDecimal value, boolean showCurrencySymbolFirst) {
        validate(value);

        Integer units = value.intValue();
        Integer subunits = value.remainder(BigDecimal.ONE).multiply(new BigDecimal(100)).intValue();

        if (showCurrencySymbolFirst) {
            if (subunits == 0) {
                return String.format(FORMAT_NO_SUBUNIT, currencySymbol, converter.asWords(units));
            }
            return String.format(FORMAT_FULL, currencySymbol, converter.asWords(units), subunits);
        } else {
            if (subunits == 0) {
                return String.format(FORMAT_NO_SUBUNIT, converter.asWords(units), currencySymbol);
            }
            return String.format(FORMAT_FULL, converter.asWords(units), currencySymbol, subunits);
        }
    }

    private void validate(BigDecimal value) {
        checkArgument(value.scale() <= MAXIMAL_DECIMAL_PLACES_COUNT,
                "can't transform more than %s decimal places for value %s", MAXIMAL_DECIMAL_PLACES_COUNT, value);

        checkArgument(valueLessThatIntMax(value),
                "can't transform numbers greater than Integer.MAX_VALUE for value %s", value);

        checkArgument(valueGreaterThanOrEqualToZero(value),
                "can't transform negative numbers for value %s", value);
    }

    private boolean valueLessThatIntMax(BigDecimal value) {
        return value.compareTo(new BigDecimal(Integer.MAX_VALUE).add(BigDecimal.ONE)) == -1;
    }

    private boolean valueGreaterThanOrEqualToZero(BigDecimal value) {
        return value.signum() >= 0;
    }
}
