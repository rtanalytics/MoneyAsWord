package vn.rta.moneyaswords;

import vn.rta.moneyaswords.core.Container;

/**
 * Created by Genius Doan on 05/11/2017.
 *
 * Base class to provide basic skeleton to create a converter manager.
 *
 * Known subclass:
 * @see LongConverterManager
 * @see MoneyConverterManager
 */
public abstract class BaseConverterManager<Type> {
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

    /**
     * Get container base for given language type and currency sign
     * @param languageType
     * @param newCurrencySign
     * @return a container which contain language values
     */
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

    /**
     * Base method for sub-class to implement. Use for converting number with generic type to text and return
     * @param value
     * @return text represent of the given value
     */
    public abstract String asWords(Type value);
}
