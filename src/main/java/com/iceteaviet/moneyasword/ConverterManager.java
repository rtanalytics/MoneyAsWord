package com.iceteaviet.moneyasword;

import com.iceteaviet.moneyasword.core.Container;

/**
 * Created by Genius Doan on 05/11/2017.
 */
public abstract class ConverterManager<Type> {
    public static final int ENGLISH = 0;
    public static final int VIETNAMESE = 1;
    public static final int CZECH = 2;
    public static final int GERMAN = 3;
    public static final int POLISH = 4;
    public static final int BRAZILIAN_PORTUGUESE = 5;
    public static final int RUSSIAN = 6;

    protected static Container getContainerFromLanguageType(int languageType) {
        return getContainerFromLanguageType(languageType, null);
    }

    protected static Container getContainerFromLanguageType(int languageType, String newCurrencySign) {
        switch (languageType) {
            case ENGLISH:
                return newCurrencySign == null ? Container.englishContainer() : Container.englishContainer(newCurrencySign);

            case VIETNAMESE:
                return newCurrencySign == null ? Container.vietnameseContainer() : Container.vietnameseContainer(newCurrencySign);

            case CZECH:
                return newCurrencySign == null ? Container.czechContainer() : Container.czechContainer(newCurrencySign);

            case GERMAN:
                return newCurrencySign == null ? Container.germanContainer() : Container.germanContainer(newCurrencySign);

            case POLISH:
                return newCurrencySign == null ? Container.polishContainer() : Container.polishContainer(newCurrencySign);

            case BRAZILIAN_PORTUGUESE:
                return newCurrencySign == null ? Container.brazilianPortugueseContainer() : Container.brazilianPortugueseContainer(newCurrencySign);

            case RUSSIAN:
                return newCurrencySign == null ? Container.russianContainer() : Container.russianContainer(newCurrencySign);

            default:
                return newCurrencySign == null ? Container.englishContainer() : Container.englishContainer(newCurrencySign);
        }
    }

    public abstract String asWords(Type value);
}
