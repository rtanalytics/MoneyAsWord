package vn.rta.moneyaswords.core.converters;

import vn.rta.moneyaswords.core.NumberToWordsConverter;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Main longConverter for banking money
 * Use BigDecimal as base unit to store value
 */
public class BDBankingMoneyToWordsConverter implements NumberToWordsConverter<BigDecimal> {
    /**
     * the pattern to produce text from amount of money
     */
    private static final String FORMAT_FULL = "%s %s %02d/100";
    private static final String FORMAT_NO_SUBUNIT = "%s %s";

    private static final int MAXIMAL_DECIMAL_PLACES_COUNT = 2;

    private final NumberToWordsConverter<Long> longConverter;
    private final String currencySymbol;

    public BDBankingMoneyToWordsConverter(NumberToWordsConverter<Long> converter, String currencySymbol) {
        this.longConverter = converter;
        this.currencySymbol = currencySymbol;
    }

    @Override
    public String asWords(BigDecimal value) {
        return asWords(value, false);
    }

    public String asWords(BigDecimal value, boolean showCurrencySymbolFirst) {
        validate(value);

        Long units = value.longValue();
        Integer subunits = value.remainder(BigDecimal.ONE).multiply(new BigDecimal(100)).intValue();

        if (showCurrencySymbolFirst) {
            if (subunits == 0) {
                return String.format(FORMAT_NO_SUBUNIT, currencySymbol, longConverter.asWords(units));
            }
            return String.format(FORMAT_FULL, currencySymbol, longConverter.asWords(units), subunits);
        } else {
            if (subunits == 0) {
                return String.format(FORMAT_NO_SUBUNIT, longConverter.asWords(units), currencySymbol);
            }
            return String.format(FORMAT_FULL, longConverter.asWords(units), currencySymbol, subunits);
        }
    }

    /**
     * Validate the input number
     * @param value the input number
     */
    private void validate(BigDecimal value) {
        checkArgument(value.scale() <= MAXIMAL_DECIMAL_PLACES_COUNT,
                "can't transform more than %s decimal places for value %s", MAXIMAL_DECIMAL_PLACES_COUNT, value);

        checkArgument(valueLessThatLongMax(value),
                "can't transform numbers greater than Long.MAX_VALUE for value %s", value);

        checkArgument(valueGreaterThanOrEqualToZero(value),
                "can't transform negative numbers for value %s", value);
    }

    private boolean valueLessThatLongMax(BigDecimal value) {
        return value.compareTo(new BigDecimal(Long.MAX_VALUE).add(BigDecimal.ONE)) == -1;
    }

    private boolean valueGreaterThanOrEqualToZero(BigDecimal value) {
        return value.signum() >= 0;
    }
}
