package com.iceteaviet.moneyasword.core;

import com.iceteaviet.moneyasword.core.converters.BDBankingMoneyToWordsConverter;
import com.iceteaviet.moneyasword.core.converters.IntegerToWordsConverter;
import com.iceteaviet.moneyasword.core.converters.UnderThousandIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.languages.CurrencyBaseValues;
import com.iceteaviet.moneyasword.core.languages.czech.CzechCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.czech.CzechIntegerToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.czech.CzechSmallCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.english.EnglishCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.german.GermanCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.german.GermanIntegerToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.german.GermanThousandToWordsMapper;
import com.iceteaviet.moneyasword.core.languages.polish.PolishCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.portuguese.BrazilianPortugueseCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.portuguese.PortugueseIntegerToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.portuguese.PortugueseIntegerToWordsJoiner;
import com.iceteaviet.moneyasword.core.languages.portuguese.PortugueseThousandToWordsMapper;
import com.iceteaviet.moneyasword.core.languages.russian.RussianCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.vietnamese.VietnameseCurrencyValues;
import com.iceteaviet.moneyasword.core.languages.vietnamese.VietnameseIntegerToWordsConverter;
import com.iceteaviet.moneyasword.core.languages.vietnamese.VietnameseUnderThousandIntegerToWordsMapper;
import com.iceteaviet.moneyasword.core.support.NumberProcessor;

import java.math.BigDecimal;

public final class Container {
    private final NumberToWordsConverter<Integer> integerConverter;
    private final NumberToWordsConverter<BigDecimal> bigDecimalConverter;

    private Container(CurrencyBaseValues currencyBaseValues) {
        UnderThousandIntegerToWordsMapper mapper = new UnderThousandIntegerToWordsMapper(currencyBaseValues.baseNumbers(),
                currencyBaseValues.twoDigitsNumberSeparator());

        //Create and assign attribute core
        integerConverter = new IntegerToWordsConverter(
                mapper,
                currencyBaseValues.pluralForms());
        bigDecimalConverter = new BDBankingMoneyToWordsConverter(
                integerConverter,
                currencyBaseValues.getCurrencySign());
    }

    private Container(NumberToWordsConverter<Integer> integerConverter,
                      NumberToWordsConverter<BigDecimal> bigDecimalConverter) {
        //Inject attribute from outside
        this.integerConverter = integerConverter;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public static Container polishContainer() {
        return new Container(new PolishCurrencyValues());
    }

    public static Container polishContainer(final String newCurrencySign) {
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

    public static Container russianContainer(final String newCurrencySign) {
        return new Container(new RussianCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        });
    }

    public static Container czechContainer() {
        CzechCurrencyValues czechValues = new CzechCurrencyValues();
        CzechSmallCurrencyValues czechSmallCurrencyValues = new CzechSmallCurrencyValues();

        return getCzechContainer(czechValues, czechSmallCurrencyValues);
    }

    public static Container czechContainer(final String newCurrencySign) {
        CzechCurrencyValues czechValues = new CzechCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        };
        CzechSmallCurrencyValues czechSmallCurrencyValues = new CzechSmallCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return newCurrencySign;
            }
        };

        return getCzechContainer(czechValues, czechSmallCurrencyValues);
    }

    public static Container englishContainer() {
        return new Container(new EnglishCurrencyValues());
    }

    public static Container englishContainer(final String newCurrencySign) {
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

    public static Container germanContainer(final String newCurrencySign) {
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

    public static Container brazilianPortugueseContainer(final String overrideCurrencySign) {
        BrazilianPortugueseCurrencyValues values = new BrazilianPortugueseCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return overrideCurrencySign;
            }
        };
        return getBrazilianPortugueseContainer(values);
    }

    public static Container vietnameseContainer() {
        return getVietnameseContainer(new VietnameseCurrencyValues());
    }

    public static Container vietnameseContainer(final String overrideCurrencySign) {
        return getVietnameseContainer(new VietnameseCurrencyValues() {
            @Override
            public String getCurrencySign() {
                return overrideCurrencySign;
            }
        });
    }

    //Helpers
    private static Container getVietnameseContainer(VietnameseCurrencyValues values) {
        UnderThousandIntegerToWordsMapper hundredsToStringConverter = new VietnameseUnderThousandIntegerToWordsMapper(values.baseNumbers(),
                values.twoDigitsNumberSeparator());
        VietnameseIntegerToWordsConverter converter = new VietnameseIntegerToWordsConverter(hundredsToStringConverter, values.pluralForms());
        BDBankingMoneyToWordsConverter bigDecimalConverter = new BDBankingMoneyToWordsConverter(
                converter,
                values.getCurrencySign());

        return new Container(converter, bigDecimalConverter);
    }

    private static Container getCzechContainer(CzechCurrencyValues czechValues, CzechSmallCurrencyValues czechSmallValues) {
        Container containerForBigNumbers = new Container(czechValues);
        Container containerForSmallNumbers = new Container(czechSmallValues);

        NumberProcessor processor = new NumberProcessor(containerForBigNumbers.getIntegerConverter(), containerForSmallNumbers.getIntegerConverter());
        CzechIntegerToWordsConverter integerConverter = new CzechIntegerToWordsConverter(processor, czechValues.exceptions());
        BDBankingMoneyToWordsConverter bigDecimalBankingMoneyValueConverter = new BDBankingMoneyToWordsConverter(
                integerConverter,
                czechValues.getCurrencySign());

        return new Container(integerConverter, bigDecimalBankingMoneyValueConverter);
    }

    private static Container getGermanContainer(GermanCurrencyValues values) {
        GermanThousandToWordsMapper mapper = new GermanThousandToWordsMapper(
                values.baseNumbers());

        NumberProcessor processor = new NumberProcessor(new IntegerToWordsConverter(mapper, values.pluralForms()),
                mapper);

        GermanIntegerToWordsConverter converter = new GermanIntegerToWordsConverter(processor, values.exceptions());

        BDBankingMoneyToWordsConverter bigDecimalBankingMoneyValueConverter = new BDBankingMoneyToWordsConverter(
                converter, values.getCurrencySign());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    private static Container getBrazilianPortugueseContainer(BrazilianPortugueseCurrencyValues values) {
        PortugueseThousandToWordsMapper thousandToWordsConverter = new PortugueseThousandToWordsMapper(
                values.baseNumbers(), values.exceptions());

        NumberProcessor processor = new NumberProcessor(new PortugueseIntegerToWordsJoiner(thousandToWordsConverter, values.pluralForms()), thousandToWordsConverter);
        PortugueseIntegerToWordsConverter integerToWordsConverter = new PortugueseIntegerToWordsConverter(processor, values.exceptions());

        BDBankingMoneyToWordsConverter bigDecimalBankingMoneyValueConverter = new BDBankingMoneyToWordsConverter(
                integerToWordsConverter, values.getCurrencySign());

        return new Container(integerToWordsConverter, bigDecimalBankingMoneyValueConverter);
    }

    public NumberToWordsConverter<Integer> getIntegerConverter() {
        return integerConverter;
    }

    public NumberToWordsConverter<BigDecimal> getBigDecimalConverter() {
        return bigDecimalConverter;
    }

    public BDBankingMoneyToWordsConverter getBankingMoneyConverter() {
        return (BDBankingMoneyToWordsConverter) bigDecimalConverter;
    }
}