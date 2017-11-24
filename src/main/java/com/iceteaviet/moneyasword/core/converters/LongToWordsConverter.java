package com.iceteaviet.moneyasword.core.converters;

import com.iceteaviet.moneyasword.core.GenderAwareIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.Joiner;
import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.PluralForms;
import com.iceteaviet.moneyasword.core.support.NumberChunking;
import com.iceteaviet.moneyasword.core.support.ToStringConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.reverse;

//Main converter for numbers
//Use Long as base unit
public class LongToWordsConverter implements NumberToWordsConverter<Long>, Joiner<Integer> {

    protected final GenderAwareIntegerToWordsMapper underThousandToWordMapper;
    private final NumberChunking numberChunking = new NumberChunking();
    private final List<PluralForms> pluralForms = new ArrayList<>();

    public LongToWordsConverter(GenderAwareIntegerToWordsMapper underThousandToWordMapper,
                                List<? extends PluralForms> pluralForms) {
        this.underThousandToWordMapper = underThousandToWordMapper;
        this.pluralForms.addAll(pluralForms);
    }

    public LongToWordsConverter(final NumberToWordsConverter<Integer> underThousandToWordMapper,
                                List<? extends PluralForms> pluralForms) {
        this.underThousandToWordMapper = ToStringConverter.toGenderAwareInteger(underThousandToWordMapper);
        this.pluralForms.addAll(pluralForms);
    }

    @Override
    public String asWords(Long value) {
        checkArgument(value >= 0, "can't convert negative numbers for value %d", value);

        List<Integer> valueChunks = numberChunking.chunk(value);

        while (valueChunks.size() > pluralForms.size()) {
            int currSize = pluralForms.size();

            //Double the array except the first element
            for (int i = 1; i < currSize; i++) {
                pluralForms.add(pluralForms.get(i));
            }
        }

        List<? extends PluralForms> formsToUse = reverse(pluralForms.subList(0, valueChunks.size()));

        return joinValueChunksWithForms(valueChunks.iterator(), formsToUse.iterator());
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<? extends PluralForms> formsToUse) {
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
