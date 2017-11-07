package com.iceteaviet.moneyasword.internal.languages.vietnamese;

import com.iceteaviet.moneyasword.internal.converters.HundredsToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.GenderForms;
import com.iceteaviet.moneyasword.internal.languages.GenderType;

import java.util.Map;

import static java.lang.String.format;

/**
 * Created by Genius Doan on 07/11/2017.
 */
public class VietnameseHundredsToWordsConverter extends HundredsToWordsConverter {
    protected static final String FORMAT_PATTERN_THREE_DIGITS = "%s%s%s";

    public VietnameseHundredsToWordsConverter(Map<Integer, GenderForms> baseValues, char twoDigitsNumberSeparator) {
        super(baseValues, twoDigitsNumberSeparator);
    }

    @Override
    protected String threeDigitsNumberAsString(Integer value, GenderType genderType) {
        Integer tensWithUnits = value % 100;
        Integer hundreds = value - tensWithUnits;
        return format(FORMAT_PATTERN_THREE_DIGITS, asWords(hundreds, genderType), tensWithUnits < 10 ? " lẻ " : " ", asWords(tensWithUnits, genderType));
    }
}
