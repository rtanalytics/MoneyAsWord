package com.iceteaviet.moneyasword;

/**
 * Created by Genius Doan on 05/11/2017.
 */
public interface ConverterManager<Type> {
    int ENGLISH = 0;
    int CZECH = 1;
    int GERMAN = 2;
    int POLISH = 3;
    int BRAZILIAN_PORTUGUESE = 4;
    int RUSSIAN = 5;


    String asWords(Type value);
}
