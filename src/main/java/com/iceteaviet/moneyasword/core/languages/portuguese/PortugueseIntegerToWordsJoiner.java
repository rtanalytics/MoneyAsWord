package com.iceteaviet.moneyasword.core.languages.portuguese;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.converters.IntegerToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.GenderType;
import com.iceteaviet.moneyasword.core.languages.PluralForms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PortugueseIntegerToWordsJoiner extends IntegerToWordsConverter {

    public PortugueseIntegerToWordsJoiner(NumberToWordsConverter<Integer> hundredsToWordsConverter,
                                          List<PluralForms> pluralForms) {
        super(hundredsToWordsConverter, pluralForms);
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<PluralForms> formsToUse) {
        List<String> result = new ArrayList<>();

        while (chunks.hasNext() && formsToUse.hasNext()) {
            Integer currentChunkValue = chunks.next();
            PluralForms currentForms = formsToUse.next();

            if (currentChunkValue > 0) {
                result.add(hundredsToWordsConverter.asWords(currentChunkValue, GenderType.GENDERLESS));
                result.add(currentForms.formFor(currentChunkValue));
            }
        }

        return joinParts(result);
    }
}