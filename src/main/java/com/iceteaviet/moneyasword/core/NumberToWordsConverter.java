package com.iceteaviet.moneyasword.core;

/**
 * Created by Genius Doan on 05/11/2017.
 */
public interface NumberToWordsConverter<Type> {
    String asWords(Type value);
}
