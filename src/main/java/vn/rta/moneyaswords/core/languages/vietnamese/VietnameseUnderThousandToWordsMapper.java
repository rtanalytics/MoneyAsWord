package vn.rta.moneyaswords.core.languages.vietnamese;

import vn.rta.moneyaswords.core.converters.UnderThousandToWordsMapper;
import vn.rta.moneyaswords.core.languages.GenderForms;
import vn.rta.moneyaswords.core.languages.GenderType;

import java.util.Map;

import static java.lang.String.format;

/**
 * Created by Genius Doan on 07/11/2017.
 *
 * Mapper between Vietnamese words and numbers
 */
public class VietnameseUnderThousandToWordsMapper extends UnderThousandToWordsMapper {
    protected static final String FORMAT_PATTERN_THREE_DIGITS = "%s%s%s";

    public VietnameseUnderThousandToWordsMapper(Map<Integer, GenderForms> baseValues, char twoDigitsNumberSeparator) {
        super(baseValues, twoDigitsNumberSeparator);
    }

    @Override
    protected String threeDigitsNumberAsString(Integer value, GenderType genderType) {
        Integer tensWithUnits = value % 100;
        Integer hundreds = value - tensWithUnits;
        return format(FORMAT_PATTERN_THREE_DIGITS, asWords(hundreds, genderType), tensWithUnits < 10 ? " lẻ " : " ", asWords(tensWithUnits, genderType));
    }
}
