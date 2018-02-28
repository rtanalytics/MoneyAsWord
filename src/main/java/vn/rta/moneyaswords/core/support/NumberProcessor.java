package vn.rta.moneyaswords.core.support;

import com.google.common.base.Joiner;
import vn.rta.moneyaswords.core.GenderAwareIntegerToWordsMapper;
import vn.rta.moneyaswords.core.NumberToWordsConverter;
import vn.rta.moneyaswords.core.languages.GenderType;

import java.util.ArrayList;
import java.util.List;

/**
 * Process number and return text.
 */
public class NumberProcessor {

    private final NumberToWordsConverter<Long> bigNumbersConverter;
    private final GenderAwareIntegerToWordsMapper smallNumbersConverter;

    public NumberProcessor(NumberToWordsConverter<Long> bigNumbersConverter,
                           NumberToWordsConverter<Integer> smallNumbersConverter) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.smallNumbersConverter = ToStringConverter.toGenderAwareInteger(smallNumbersConverter);
    }

    public NumberProcessor(NumberToWordsConverter<Long> bigNumbersConverter,
                           GenderAwareIntegerToWordsMapper smallNumbersConverter) {
        this.bigNumbersConverter = bigNumbersConverter;
        this.smallNumbersConverter = smallNumbersConverter;
    }

    public String process(Long bigNumber, Integer smallNumber) {
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
