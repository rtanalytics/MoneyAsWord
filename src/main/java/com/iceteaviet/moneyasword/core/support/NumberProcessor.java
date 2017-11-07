package com.iceteaviet.moneyasword.core.support;

import com.google.common.base.Joiner;
import com.iceteaviet.moneyasword.core.GenderAwareIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.GenderType;

import java.util.ArrayList;
import java.util.List;

public class NumberProcessor {

    private final NumberToWordsConverter<Integer> bigNumbersConverter;
    private final GenderAwareIntegerToWordsMapper smallNumbersConverter;

    public NumberProcessor(NumberToWordsConverter<Integer> bigNumbersConverter,
                           NumberToWordsConverter<Integer> smallNumbersConverter) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.smallNumbersConverter = ToStringConverter.toGenderAwareInteger(smallNumbersConverter);
    }

    public NumberProcessor(NumberToWordsConverter<Integer> bigNumbersConverter,
                           GenderAwareIntegerToWordsMapper smallNumbersConverter) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.smallNumbersConverter = smallNumbersConverter;
    }

    public String process(Integer bigNumber, Integer smallNumber) {
        List<String> result = new ArrayList<>();

        if (bigNumber > 0) {
            result.add(bigNumbersConverter.asWords(bigNumber));
        }

        if (smallNumber > 0) {
            result.add(smallNumbersConverter.asWords(smallNumber, GenderType.GENDERLESS));
        }

        return merge(result);
    }

    private String merge(List<String> result) {
        if (result.isEmpty()) {
            return smallNumbersConverter.asWords(0, GenderType.GENDERLESS);
        }

        return Joiner.on(" ").join(result);
    }
}
