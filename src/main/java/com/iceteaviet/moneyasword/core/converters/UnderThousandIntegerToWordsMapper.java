package com.iceteaviet.moneyasword.core.converters;

import com.google.common.collect.Range;
import com.iceteaviet.moneyasword.core.GenderAwareIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.languages.GenderForms;
import com.iceteaviet.moneyasword.core.languages.GenderType;

import java.util.Map;

import static java.lang.String.format;

public class UnderThousandIntegerToWordsMapper implements GenderAwareIntegerToWordsMapper {
    protected static final String FORMAT_PATTERN_THREE_DIGITS = "%s %s";
    protected static final String FORMAT_PATTERN_TWO_DIGITS = "%s%c%s";
    protected final Map<Integer, GenderForms> baseValues;
    protected final char twoDigitsNumberSeparator;

    public UnderThousandIntegerToWordsMapper(Map<Integer, GenderForms> baseValues, char twoDigitsNumberSeparator) {
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
