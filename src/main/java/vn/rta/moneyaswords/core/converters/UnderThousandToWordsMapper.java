package vn.rta.moneyaswords.core.converters;

import com.google.common.collect.Range;
import vn.rta.moneyaswords.core.GenderAwareIntegerToWordsMapper;
import vn.rta.moneyaswords.core.languages.GenderForms;
import vn.rta.moneyaswords.core.languages.GenderType;

import java.util.Map;

import static java.lang.String.format;

/**
 * Convert under thousand numbers to text
 */
public class UnderThousandToWordsMapper implements GenderAwareIntegerToWordsMapper {
    protected static final String FORMAT_PATTERN_THREE_DIGITS = "%s %s";
    protected static final String FORMAT_PATTERN_TWO_DIGITS = "%s%c%s";
    protected final Map<Integer, GenderForms> baseValues;
    protected final char twoDigitsNumberSeparator;

    public UnderThousandToWordsMapper(Map<Integer, GenderForms> baseValues, char twoDigitsNumberSeparator) {
        this.baseValues = baseValues;
        this.twoDigitsNumberSeparator = twoDigitsNumberSeparator;
    }

    @Override
    public String asWords(Integer value, GenderType genderType) {
        if (baseValues.containsKey(value)) {
            return baseValues.get(value).formFor(genderType);
        } else if (Range.closed(21, 99).contains(value)) {
            return twoDigitsNumberAsString(value, genderType);
        } else if (Range.closed(101, 999).contains(value)) {
            return threeDigitsNumberAsString(value, genderType);
        }

        throw new IllegalArgumentException(format("Can't convert %d", value));
    }

    protected String twoDigitsNumberAsString(Integer value, GenderType genderType) {
        Integer units = value % 10;
        Integer tens = value - units;
        return format(FORMAT_PATTERN_TWO_DIGITS, asWords(tens, genderType), twoDigitsNumberSeparator, asWords(units, genderType));
    }

    protected String threeDigitsNumberAsString(Integer value, GenderType genderType) {
        Integer tensWithUnits = value % 100;
        Integer hundreds = value - tensWithUnits;
        return format(FORMAT_PATTERN_THREE_DIGITS, asWords(hundreds, genderType), asWords(tensWithUnits, genderType));
    }
}
