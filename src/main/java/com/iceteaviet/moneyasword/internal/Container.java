package com.iceteaviet.moneyasword.internal;

import com.iceteaviet.moneyasword.internal.converters.BigDecimalToBankingMoneyConverter;
import com.iceteaviet.moneyasword.internal.converters.HundredsToWordsConverter;
import com.iceteaviet.moneyasword.internal.converters.IntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.czech.CzechIntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.czech.CzechValues;
import com.iceteaviet.moneyasword.internal.languages.czech.CzechValuesForSmallNumbers;
import com.iceteaviet.moneyasword.internal.languages.english.EnglishValues;
import com.iceteaviet.moneyasword.internal.languages.german.GermanIntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.german.GermanThousandToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.german.GermanValues;
import com.iceteaviet.moneyasword.internal.languages.polish.PolishValues;
import com.iceteaviet.moneyasword.internal.languages.portuguese.BrazilianPortugueseValues;
import com.iceteaviet.moneyasword.internal.languages.portuguese.PortugueseIntegerToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.portuguese.PortugueseIntegerToWordsConverterAdapter;
import com.iceteaviet.moneyasword.internal.languages.portuguese.PortugueseThousandToWordsConverter;
import com.iceteaviet.moneyasword.internal.languages.russian.RussianValues;
import com.iceteaviet.moneyasword.internal.languages.vietnamese.VietnameseValues;

import java.math.BigDecimal;

public final class Container {

    private final NumberToStringConverter<Integer> integerConverter;
    private final NumberToStringConverter<BigDecimal> bigDecimalConverter;

    private Container(BaseValues baseValues) {
        HundredsToWordsConverter hundredsToStringConverter = new HundredsToWordsConverter(baseValues.baseNumbers(),
                baseValues.twoDigitsNumberSeparator());

        integerConverter = new IntegerToWordsConverter(
                hundredsToStringConverter,
                baseValues.pluralForms());
        bigDecimalConverter = new BigDecimalToBankingMoneyConverter(
                integerConverter,
                baseValues.currency());
    }

    private Container(NumberToStringConverter<Integer> integerConverter,
                      NumberToStringConverter<BigDecimal> bigDecimalConverter) {
        this.integerConverter = integerConverter;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public static Container polishContainer() {
        return new Container(new PolishValues());
    }

    public static Container russianContainer() {
        return new Container(new RussianValues());
    }

    public static Container czechContainer() {
        CzechValues czechValues = new CzechValues();
        Container containerForBigNumbers = new Container(czechValues);
        Container containerForSmallNumbers = new Container(new CzechValuesForSmallNumbers());

        NumberToStringConverter<Integer> integerConverter = new CzechIntegerToWordsConverter(
                containerForBigNumbers.getNumbersConverter(), containerForSmallNumbers.getNumbersConverter(),
                czechValues.exceptions()
        );
        NumberToStringConverter<BigDecimal> bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                integerConverter,
                czechValues.currency());

        return new Container(integerConverter, bigDecimalBankingMoneyValueConverter);
    }

    public static Container englishContainer() {
        return new Container(new EnglishValues());
    }

    public static Container germanContainer() {

        GermanValues values = new GermanValues();

        GermanThousandToWordsConverter germanThousandToWordsConverter = new GermanThousandToWordsConverter(
                values.baseNumbers());

        NumberToStringConverter<Integer> converter = new GermanIntegerToWordsConverter(
                new IntegerToWordsConverter(germanThousandToWordsConverter, values.pluralForms()), values.exceptions(),
                germanThousandToWordsConverter);

        NumberToStringConverter<BigDecimal> bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                converter, values.currency());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    public static Container brazilianPortugueseContainer() {
        BrazilianPortugueseValues values = new BrazilianPortugueseValues();

        PortugueseThousandToWordsConverter portugueseThousandToWordsConverter = new PortugueseThousandToWordsConverter(
                values.baseNumbers(), values.exceptions());

        NumberToStringConverter<Integer> converter = new PortugueseIntegerToWordsConverter(
                new PortugueseIntegerToWordsConverterAdapter(portugueseThousandToWordsConverter, values.pluralForms()), values.exceptions(),
                portugueseThousandToWordsConverter);

        NumberToStringConverter<BigDecimal> bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                converter, values.currency());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    public static Container vietnameseContainer() {
        return new Container(new VietnameseValues());
    }

    public NumberToStringConverter<Integer> getNumbersConverter() {
        return integerConverter;
    }

    public NumberToStringConverter<BigDecimal> getBankingMoneyConverter() {
        return bigDecimalConverter;
    }
}