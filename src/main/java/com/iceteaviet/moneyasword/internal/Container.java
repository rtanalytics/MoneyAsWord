package com.iceteaviet.moneyasword.internal;

import com.iceteaviet.moneyasword.internal.converters.BigDecimalToBankingMoneyConverter;
import com.iceteaviet.moneyasword.internal.converters.HundredsToWordsConverter;
import com.iceteaviet.moneyasword.internal.converters.IntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.CurrencyBaseValues;
import com.iceteaviet.moneyasword.internal.languages.czech.CzechCurrencyValues;
import com.iceteaviet.moneyasword.internal.languages.czech.CzechIntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.czech.CzechValuesForSmallNumbersCurrency;
import com.iceteaviet.moneyasword.internal.languages.english.EnglishCurrencyValues;
import com.iceteaviet.moneyasword.internal.languages.german.GermanCurrencyValues;
import com.iceteaviet.moneyasword.internal.languages.german.GermanIntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.german.GermanThousandToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.polish.PolishCurrencyValues;
import com.iceteaviet.moneyasword.internal.languages.portuguese.BrazilianPortugueseCurrencyValues;
import com.iceteaviet.moneyasword.internal.languages.portuguese.PortugueseIntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.portuguese.PortugueseIntegerToWordsConverterAdapter;
import com.iceteaviet.moneyasword.internal.languages.portuguese.PortugueseThousandToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.russian.RussianCurrencyValues;
import com.iceteaviet.moneyasword.internal.languages.vietnamese.VietnameseCurrencyValues;

import java.math.BigDecimal;

public final class Container {
    private final NumberToStringConverter<Integer> integerConverter;
    private final NumberToStringConverter<BigDecimal> bigDecimalConverter;

    private Container(CurrencyBaseValues currencyBaseValues) {
        HundredsToWordsConverter hundredsToStringConverter = new HundredsToWordsConverter(currencyBaseValues.baseNumbers(),
                currencyBaseValues.twoDigitsNumberSeparator());

        integerConverter = new IntegerToWordsConverter(
                hundredsToStringConverter,
                currencyBaseValues.pluralForms());
        bigDecimalConverter = new BigDecimalToBankingMoneyConverter(
                integerConverter,
                currencyBaseValues.getCurrencySign());
    }

    private Container(NumberToStringConverter<Integer> integerConverter,
                      NumberToStringConverter<BigDecimal> bigDecimalConverter) {
        this.integerConverter = integerConverter;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public static Container polishContainer() {
        return new Container(new PolishCurrencyValues());
    }

    public static Container polishContainer(String newCurrencySign) {
        return new Container(new PolishCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        });
    }

    public static Container russianContainer() {
        return new Container(new RussianCurrencyValues());
    }

    public static Container russianContainer(String newCurrencySign) {
        return new Container(new RussianCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        });
    }

    public static Container czechContainer() {
        CzechCurrencyValues czechValues = new CzechCurrencyValues();
        CzechValuesForSmallNumbersCurrency czechValuesForSmallNumbersCurrency = new CzechValuesForSmallNumbersCurrency();

        return getCzechContainer(czechValues, czechValuesForSmallNumbersCurrency);
    }

    public static Container czechContainer(String newCurrencySign) {
        CzechCurrencyValues czechValues = new CzechCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        };
        CzechValuesForSmallNumbersCurrency czechValuesForSmallNumbersCurrency = new CzechValuesForSmallNumbersCurrency() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        };

        return getCzechContainer(czechValues, czechValuesForSmallNumbersCurrency);
    }

    public static Container englishContainer() {
        return new Container(new EnglishCurrencyValues());
    }

    public static Container englishContainer(String newCurrencySign) {
        return new Container(new EnglishCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        });
    }

    public static Container germanContainer() {
        GermanCurrencyValues values = new GermanCurrencyValues();
        return getGermanContainer(values);
    }

    public static Container germanContainer(String newCurrencySign) {
        GermanCurrencyValues values = new GermanCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        };
        return getGermanContainer(values);
    }

    public static Container brazilianPortugueseContainer() {
        BrazilianPortugueseCurrencyValues values = new BrazilianPortugueseCurrencyValues();
        return getBrazilianPortugueseContainer(values);
    }

    public static Container brazilianPortugueseContainer(String overrideCurrencySign) {
        BrazilianPortugueseCurrencyValues values = new BrazilianPortugueseCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return overrideCurrencySign;
            }
        };
        return getBrazilianPortugueseContainer(values);
    }

    public static Container vietnameseContainer() {
        return new Container(new VietnameseCurrencyValues());
    }

    public static Container vietnameseContainer(String overrideCurrencySign) {
        return new Container(new VietnameseCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return overrideCurrencySign;
            }
        });
    }

    //Helpers
    private static Container getCzechContainer(CzechCurrencyValues czechValues, CzechValuesForSmallNumbersCurrency czechSmallValues) {
        Container containerForBigNumbers = new Container(czechValues);
        Container containerForSmallNumbers = new Container(czechSmallValues);

        CzechIntegerToWordsConverter integerConverter = new CzechIntegerToWordsConverter(
                containerForBigNumbers.getIntegerConverter(), containerForSmallNumbers.getIntegerConverter(),
                czechValues.exceptions()
        );
        BigDecimalToBankingMoneyConverter bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                integerConverter,
                czechValues.getCurrencySign());

        return new Container(integerConverter, bigDecimalBankingMoneyValueConverter);
    }

    private static Container getGermanContainer(GermanCurrencyValues germanCurrencyValues) {
        GermanThousandToWordsConverter germanThousandToWordsConverter = new GermanThousandToWordsConverter(
                germanCurrencyValues.baseNumbers());

        GermanIntegerToWordsConverter converter = new GermanIntegerToWordsConverter(
                new IntegerToWordsConverter(germanThousandToWordsConverter, germanCurrencyValues.pluralForms()), germanCurrencyValues.exceptions(),
                germanThousandToWordsConverter);

        BigDecimalToBankingMoneyConverter bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                converter, germanCurrencyValues.getCurrencySign());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    private static Container getBrazilianPortugueseContainer(BrazilianPortugueseCurrencyValues values) {
        PortugueseThousandToWordsConverter portugueseThousandToWordsConverter = new PortugueseThousandToWordsConverter(
                values.baseNumbers(), values.exceptions());

        PortugueseIntegerToWordsConverter converter = new PortugueseIntegerToWordsConverter(
                new PortugueseIntegerToWordsConverterAdapter(portugueseThousandToWordsConverter, values.pluralForms()), values.exceptions(),
                portugueseThousandToWordsConverter);

        BigDecimalToBankingMoneyConverter bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                converter, values.getCurrencySign());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    public NumberToStringConverter<Integer> getIntegerConverter() {
        return integerConverter;
    }

    public NumberToStringConverter<BigDecimal> getBigDecimalConverter() {
        return bigDecimalConverter;
    }

    public BigDecimalToBankingMoneyConverter getBankingMoneyConverter() {
        return (BigDecimalToBankingMoneyConverter) bigDecimalConverter;
    }
}