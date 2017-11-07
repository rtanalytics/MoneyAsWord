package com.iceteaviet.moneyasword.core;

import com.iceteaviet.moneyasword.core.languages.PluralForms;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 */
public interface Joiner<Type> {
    String joinValueChunksWithForms(Iterator<Type> chunks, Iterator<PluralForms> formsToUse);

    String joinParts(List<String> result);
}
