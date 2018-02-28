package vn.rta.moneyaswords.core.converters;

import vn.rta.moneyaswords.core.GenderAwareIntegerToWordsMapper;
import vn.rta.moneyaswords.core.Joiner;
import vn.rta.moneyaswords.core.NumberToWordsConverter;
import vn.rta.moneyaswords.core.languages.PluralForms;
import vn.rta.moneyaswords.core.support.NumberChunking;
import vn.rta.moneyaswords.core.support.ToStringConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.reverse;

/**
 * Created by Genius Doan on 23/11/2017.
 *
 * Main converter for numbers
 * Use Integer as base unit
 *
 * @deprecated since 21/02/2018. It's not safe to use Integer as it can cause number overflow when convert big numbers.
 * @see LongToWordsConverter
 */

@Deprecated
public class IntegerToWordsConverter<P extends PluralForms> implements NumberToWordsConverter<Integer>, Joiner<Integer, P> {

    protected final GenderAwareIntegerToWordsMapper underThousandToWordMapper;
    private final NumberChunking numberChunking = new NumberChunking();
    private final List<P> pluralForms;

    public IntegerToWordsConverter(GenderAwareIntegerToWordsMapper underThousandToWordMapper,
                                   List<P> pluralForms) {
        this.underThousandToWordMapper = underThousandToWordMapper;
        this.pluralForms = pluralForms;
    }

    public IntegerToWordsConverter(final NumberToWordsConverter<Integer> underThousandToWordMapper,
                                   List<P> pluralForms) {
        this.underThousandToWordMapper = ToStringConverter.toGenderAwareInteger(underThousandToWordMapper);
        this.pluralForms = pluralForms;
    }

    @Override
    public String asWords(Integer value) {
        checkArgument(value >= 0, "can't convert negative numbers for value %d", value);

        List<Integer> valueChunks = numberChunking.chunk(value);
        List<P> formsToUse = reverse(pluralForms.subList(0, valueChunks.size()));

        return joinValueChunksWithForms(valueChunks.iterator(), formsToUse.iterator());
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<P> formsToUse) {
        List<String> result = new ArrayList<>();

        while (chunks.hasNext() && formsToUse.hasNext()) {
            Integer currentChunkValue = chunks.next();
            PluralForms currentForms = formsToUse.next();

            if (currentChunkValue > 0) {
                result.add(underThousandToWordMapper.asWords(currentChunkValue, currentForms.genderType()));
                result.add(currentForms.formFor(currentChunkValue));
            }
        }

        return joinParts(result);
    }

    @Override
    public String joinParts(List<String> result) {
        if (result.size() == 0) {
            return underThousandToWordMapper.asWords(0, pluralForms.get(0).genderType());
        }

        return com.google.common.base.Joiner.on(" ").join(result).trim();
    }
}
