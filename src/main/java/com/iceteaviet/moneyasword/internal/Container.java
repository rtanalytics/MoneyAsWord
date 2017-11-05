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

public final class Container {

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

        IntegerToStringConverter integerConverter = new CzechIntegerToWordsConverter(
                containerForBigNumbers.getNumbersConverter(), containerForSmallNumbers.getNumbersConverter(),
                czechValues.exceptions()
        );
        BigDecimalToStringConverter bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
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

        IntegerToStringConverter converter = new GermanIntegerToWordsConverter(
                new IntegerToWordsConverter(germanThousandToWordsConverter, values.pluralForms()), values.exceptions(),
                germanThousandToWordsConverter);

        BigDecimalToStringConverter bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                converter, values.currency());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    public static Container brazilianPortugueseContainer() {
        BrazilianPortugueseValues values = new BrazilianPortugueseValues();

        PortugueseThousandToWordsConverter portugueseThousandToWordsConverter = new PortugueseThousandToWordsConverter(
                values.baseNumbers(), values.exceptions());

        IntegerToStringConverter converter = new PortugueseIntegerToWordsConverter(
                new PortugueseIntegerToWordsConverterAdapter(portugueseThousandToWordsConverter, values.pluralForms()), values.exceptions(),
                portugueseThousandToWordsConverter);

        BigDecimalToStringConverter bigDecimalBankingMoneyValueConverter = new BigDecimalToBankingMoneyConverter(
                converter, values.currency());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    private final IntegerToStringConverter integerConverter;
    private final BigDecimalToStringConverter bigDecimalConverter;

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

    private Container(IntegerToStringConverter integerConverter,
                      BigDecimalToStringConverter bigDecimalConverter) {
        this.integerConverter = integerConverter;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public IntegerToStringConverter getNumbersConverter() {
        return integerConverter;
    }

    public BigDecimalToStringConverter getBankingMoneyConverter() {
        return bigDecimalConverter;
    }
}