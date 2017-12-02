package com.iceteaviet.moneyasword.core.converters;

import com.iceteaviet.moneyasword.core.Joiner;
import com.iceteaviet.moneyasword.core.NumberToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.PluralForms;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 */


public class BDToWordsConverter implements NumberToWordsConverter<BigDecimal>, Joiner<BigDecimal> {
    @Override
    public String asWords(BigDecimal value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String joinValueChunksWithForms(Iterator<BigDecimal> chunks, Iterator<? extends PluralForms> formsToUse) {
        return null;
    }

    @Override
    public String joinParts(List<String> result) {
        return null;
    }
}
