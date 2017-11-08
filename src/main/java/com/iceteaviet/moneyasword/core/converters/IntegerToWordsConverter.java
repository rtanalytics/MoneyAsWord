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
//Use Integer as base unit
public class IntegerToWordsConverter implements NumberToWordsConverter<Integer>, Joiner<Integer> {

    protected final GenderAwareIntegerToWordsMapper hundredsToWordsConverter;
    private final NumberChunking numberChunking = new NumberChunking();
    private final List<? extends PluralForms> pluralForms;

    public IntegerToWordsConverter(GenderAwareIntegerToWordsMapper hundredsToWordsConverter,
                                   List<? extends PluralForms> pluralForms) {
        this.hundredsToWordsConverter = hundredsToWordsConverter;
        this.pluralForms = pluralForms;
    }

    public IntegerToWordsConverter(final NumberToWordsConverter<Integer> hundredsToWordsConverter,
                                   List<? extends PluralForms> pluralForms) {
        this.hundredsToWordsConverter = ToStringConverter.toGenderAwareInteger(hundredsToWordsConverter);
        this.pluralForms = pluralForms;
    }

    @Override
    public String asWords(Integer value) {
        checkArgument(value >= 0, "can't convert negative numbers for value %d", value);

        List<Integer> valueChunks = numberChunking.chunk(value);
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
                result.add(hundredsToWordsConverter.asWords(currentChunkValue, currentForms.genderType()));
                result.add(currentForms.formFor(currentChunkValue));
            }
        }

        return joinParts(result);
    }

    @Override
    public String joinParts(List<String> result) {
        if (result.size() == 0) {
            return hundredsToWordsConverter.asWords(0, pluralForms.get(0).genderType());
        }

        return com.google.common.base.Joiner.on(" ").join(result).trim();
    }
}
