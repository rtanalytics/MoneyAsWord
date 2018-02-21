package com.iceteaviet.moneyasword.core.languages.vietnamese;

import com.iceteaviet.moneyasword.core.GenderAwareIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.converters.LongToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.GenderType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 *
 * Convert Vietnamese money to text
 */

public class VietnameseLongToWordsConverter extends LongToWordsConverter<VietnamesePluralForms> {
    public VietnameseLongToWordsConverter(GenderAwareIntegerToWordsMapper underThousandToWordMapper,
                                          List<VietnamesePluralForms> pluralForms) {
        super(underThousandToWordMapper, pluralForms);
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<VietnamesePluralForms> formsToUse) {
        List<String> result = new ArrayList<>();

        while (chunks.hasNext() && formsToUse.hasNext()) {
            Integer currentChunkValue = chunks.next();
            VietnamesePluralForms currentForms = formsToUse.next();

            //Add number if digit > 0.
            // If we don't use this condition: it will be return redundant string like: 9.000.000.000 -> nine billion zero million zero thousand (should be only: nine billion)
            // Or: 9.000.000.000.000 -> chín nghìn không tỉ không triệu không nghìn (should be: chín nghìn tỷ)
            if (currentChunkValue > 0) {
                if (isHaveMiddleBufferWord(currentChunkValue, !result.isEmpty())) {
                    if (currentChunkValue < 10)
                        result.add("không trăm lẻ");
                    else
                        result.add("không trăm");
                }

                result.add(underThousandToWordMapper.asWords(currentChunkValue, GenderType.GENDERLESS));
                result.add(currentForms.formFor(currentChunkValue));
            } else if (currentForms.isForcedKeep()) {
                result.add(currentForms.formFor(currentChunkValue));
            }
        }

        return joinParts(result);
    }

    /**
     * Vietnamese sometimes have the "buffer word" like: "lẻ", "linh" between two words.
     * @param currentChunkValue
     * @param notFirstDigit
     * @return a boolean value show if it need to insert middle buffer word or not.
     */
    private boolean isHaveMiddleBufferWord(Integer currentChunkValue, boolean notFirstDigit) {
        return currentChunkValue < 100 && notFirstDigit;
    }
}
