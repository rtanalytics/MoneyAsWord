package com.iceteaviet.moneyasword.core.languages.portuguese;

import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.converters.LongToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.GenderType;
import com.iceteaviet.moneyasword.core.languages.PluralForms;
import com.iceteaviet.moneyasword.core.languages.shared.RegularPluralForms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PortugueseLongToWordsJoiner extends LongToWordsConverter<RegularPluralForms> {

    public PortugueseLongToWordsJoiner(NumberToWordsConverter<Integer> underThousandToWordMapper,
                                       List<RegularPluralForms> pluralForms) {
        super(underThousandToWordMapper, pluralForms);
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<RegularPluralForms> formsToUse) {
        List<String> result = new ArrayList<>();

        while (chunks.hasNext() && formsToUse.hasNext()) {
            Integer currentChunkValue = chunks.next();
            PluralForms currentForms = formsToUse.next();

            if (currentChunkValue > 0) {
                result.add(underThousandToWordMapper.asWords(currentChunkValue, GenderType.GENDERLESS));
                result.add(currentForms.formFor(currentChunkValue));
            }
        }

        return joinParts(result);
    }
}