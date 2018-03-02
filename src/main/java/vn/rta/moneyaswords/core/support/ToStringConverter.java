package vn.rta.moneyaswords.core.support;

import vn.rta.moneyaswords.core.GenderAwareIntegerToWordsMapper;
import vn.rta.moneyaswords.core.NumberToWordsConverter;
import vn.rta.moneyaswords.core.languages.GenderType;

public class ToStringConverter {
    public static GenderAwareIntegerToWordsMapper toGenderAwareInteger(final NumberToWordsConverter<Integer> integerToStringConverter) {
        return new GenderAwareIntegerToWordsMapper() {
            @Override
            public String asWords(Integer value, GenderType genderType) {
                return integerToStringConverter.asWords(value);
            }
        };
    }
}
