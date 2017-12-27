package com.iceteaviet.moneyasword.core;

import com.iceteaviet.moneyasword.core.languages.PluralForms;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 */
public interface Joiner<T, P extends PluralForms> {
    String joinValueChunksWithForms(Iterator<T> chunks, Iterator<P> formsToUse);

    String joinParts(List<String> result);
}
