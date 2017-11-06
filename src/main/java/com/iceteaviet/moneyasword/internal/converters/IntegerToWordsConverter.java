package com.iceteaviet.moneyasword.internal.converters;

import com.google.common.base.Joiner;
import com.iceteaviet.moneyasword.internal.GenderAwareIntegerToStringConverter;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;
import com.iceteaviet.moneyasword.internal.ToStringConverter;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;
import com.iceteaviet.moneyasword.internal.support.NumberChunking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.reverse;

public class IntegerToWordsConverter implements NumberToStringConverter<Integer> {

    protected final GenderAwareIntegerToStringConverter hundredsToWordsConverter;
    private final NumberChunking numberChunking = new NumberChunking();
    private final List<PluralForms> pluralForms;

    public IntegerToWordsConverter(GenderAwareIntegerToStringConverter hundredsToWordsConverter,
                                   List<PluralForms> pluralForms) {
        this.hundredsToWordsConverter = hundredsToWordsConverter;
        this.pluralForms = pluralForms;
    }

    public IntegerToWordsConverter(final NumberToStringConverter<Integer> hundredsToWordsConverter,
                                   List<PluralForms> pluralForms) {
        this.hundredsToWordsConverter = ToStringConverter.toGenderAwareInteger(hundredsToWordsConverter);
        this.pluralForms = pluralForms;
    }

    @Override
    public String asWords(Integer value) {
        checkArgument(value >= 0, "can't convert negative numbers for value %d", value);

        List<Integer> valueChunks = numberChunking.chunk(value);
        List<PluralForms> formsToUse = reverse(pluralForms.subList(0, valueChunks.size()));

        return joinValueChunksWithForms(valueChunks.iterator(), formsToUse.iterator());
    }

    protected String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<PluralForms> formsToUse) {
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

    protected String joinParts(List<String> result) {
        if (result.size() == 0) {
            return hundredsToWordsConverter.asWords(0, pluralForms.get(0).genderType());
        }

        return Joiner.on(" ").join(result).trim();
    }
}
