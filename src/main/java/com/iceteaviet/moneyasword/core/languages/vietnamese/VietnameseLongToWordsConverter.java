package com.iceteaviet.moneyasword.core.languages.vietnamese;

import com.iceteaviet.moneyasword.core.GenderAwareIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.converters.LongToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.GenderType;
import com.iceteaviet.moneyasword.core.languages.PluralForms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 */
public class VietnameseLongToWordsConverter extends LongToWordsConverter {
    public VietnameseLongToWordsConverter(GenderAwareIntegerToWordsMapper underThousandToWordMapper,
                                          List<? extends PluralForms> pluralForms) {
        super(underThousandToWordMapper, pluralForms);
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<? extends PluralForms> formsToUse) {
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

                result.add(underThousandToWordMapper.asWords(currentChunkValue, GenderType.GENDERLESS));
                result.add(pluralFormString);
            }
        }

        return joinParts(result);
    }

    private boolean isHaveMiddleBufferWord(Integer currentChunkValue, boolean notFirstDigit) {
        return currentChunkValue < 100 && notFirstDigit;
    }
}
