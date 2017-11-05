package com.iceteaviet.moneyasword.internal;

/**
 * Created by Genius Doan on 05/11/2017.
 */
public interface NumberToStringConverter<Type> {
    String asWords(Type value);
}
