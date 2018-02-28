package vn.rta.moneyaswords.core.support;

import vn.rta.moneyaswords.core.GenderAwareIntegerToWordsMapper;
import vn.rta.moneyaswords.core.NumberToWordsConverter;

public class ToStringConverter {
    public static GenderAwareIntegerToWordsMapper toGenderAwareInteger(final NumberToWordsConverter<Integer> integerToStringConverter) {
        return (value, genderType) -> integerToStringConverter.asWords(value);
    }
}
