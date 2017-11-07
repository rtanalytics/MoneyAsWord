package com.iceteaviet.moneyasword.internal;

import com.iceteaviet.moneyasword.internal.languages.PluralForms;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 */
public interface Joinable<Type> {
    String joinValueChunksWithForms(Iterator<Type> chunks, Iterator<PluralForms> formsToUse);
    String joinParts(List<String> result);
}
