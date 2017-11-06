package com.iceteaviet.moneyasword;

/**
 * Created by Genius Doan on 05/11/2017.
 */
public interface ConverterManager<Type> {
    int ENGLISH = 0;
    int VIETNAMESE = 1;
    int CZECH = 2;
    int GERMAN = 3;
    int POLISH = 4;
    int BRAZILIAN_PORTUGUESE = 5;
    int RUSSIAN = 6;


    String asWords(Type value);
}
