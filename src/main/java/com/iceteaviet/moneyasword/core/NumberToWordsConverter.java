package com.iceteaviet.moneyasword.core;

/**
 * Created by Genius Doan on 05/11/2017.
 *
 * Convert number of the generic Type to text representation.
 */
public interface NumberToWordsConverter<Type> {
    String asWords(Type value);
}
