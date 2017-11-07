package com.iceteaviet.moneyasword.internal.languages.vietnamese;

import com.iceteaviet.moneyasword.internal.GenderAwareIntegerToStringConverter;
import com.iceteaviet.moneyasword.internal.NumberToStringConverter;
import com.iceteaviet.moneyasword.internal.converters.IntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.GenderType;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 */
public class VietnameseIntegerToWordsJoiner extends IntegerToWordsConverter {
    public VietnameseIntegerToWordsJoiner(GenderAwareIntegerToStringConverter hundredsToWordsConverter,
                                          List<PluralForms> pluralForms) {
        super(hundredsToWordsConverter, pluralForms);
    }

    public VietnameseIntegerToWordsJoiner(NumberToStringConverter<Integer> hundredsToWordsConverter,
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
                String pluralFormString = currentForms.formFor(currentChunkValue);
                if (isHaveMiddleBufferWord(currentChunkValue, !result.isEmpty())) {
                    if (currentChunkValue < 10)
                        result.add("không trăm lẻ");
                    else
                        result.add("không trăm");
                }

                result.add(hundredsToWordsConverter.asWords(currentChunkValue, GenderType.GENDERLESS));
                result.add(pluralFormString);
            }
        }

        return joinParts(result);
    }

    private boolean isHaveMiddleBufferWord(Integer currentChunkValue, boolean notFirstDigit) {
        return currentChunkValue < 100 && notFirstDigit;
    }
}
