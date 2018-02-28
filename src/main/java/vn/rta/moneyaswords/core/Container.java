package vn.rta.moneyaswords.core;

import vn.rta.moneyaswords.core.converters.BDBankingMoneyToWordsConverter;
import vn.rta.moneyaswords.core.converters.IntegerToWordsConverter;
import vn.rta.moneyaswords.core.converters.LongToWordsConverter;
import vn.rta.moneyaswords.core.converters.UnderThousandToWordsMapper;
import vn.rta.moneyaswords.core.languages.CurrencyBaseValues;
import vn.rta.moneyaswords.core.languages.czech.CzechCurrencyValues;
import vn.rta.moneyaswords.core.languages.czech.CzechLongToWordsConverter;
import vn.rta.moneyaswords.core.languages.czech.CzechSmallCurrencyValues;
import vn.rta.moneyaswords.core.languages.english.EnglishCurrencyValues;
import vn.rta.moneyaswords.core.languages.german.GermanCurrencyValues;
import vn.rta.moneyaswords.core.languages.german.GermanLongToWordsConverter;
import vn.rta.moneyaswords.core.languages.german.GermanThousandToWordsMapper;
import vn.rta.moneyaswords.core.languages.polish.PolishCurrencyValues;
import vn.rta.moneyaswords.core.languages.portuguese.BrazilianPortugueseCurrencyValues;
import vn.rta.moneyaswords.core.languages.portuguese.PortugueseLongToWordsConverter;
import vn.rta.moneyaswords.core.languages.portuguese.PortugueseLongToWordsJoiner;
import vn.rta.moneyaswords.core.languages.portuguese.PortugueseThousandToWordsMapper;
import vn.rta.moneyaswords.core.languages.russian.RussianCurrencyValues;
import vn.rta.moneyaswords.core.languages.vietnamese.VietnameseCurrencyValues;
import vn.rta.moneyaswords.core.languages.vietnamese.VietnameseLongToWordsConverter;
import vn.rta.moneyaswords.core.languages.vietnamese.VietnameseUnderThousandToWordsMapper;
import vn.rta.moneyaswords.core.support.NumberProcessor;

import java.math.BigDecimal;

/**
 * Contain language values and converter for converting number to text for specific language.
 */

public final class Container {
    /**
     * Use for convert long number to text
     */
    private final NumberToWordsConverter<Long> longConverter;

    /**
     * Use for convert big decimal number to text
     */
    private final NumberToWordsConverter<BigDecimal> bigDecimalConverter;

    private Container(CurrencyBaseValues currencyBaseValues) {
        UnderThousandToWordsMapper mapper = new UnderThousandToWordsMapper(currencyBaseValues.baseNumbers(),
                currencyBaseValues.twoDigitsNumberSeparator());

        //Create and assign attribute core
        longConverter = new LongToWordsConverter(
                mapper,
                currencyBaseValues.pluralForms());
        bigDecimalConverter = new BDBankingMoneyToWordsConverter(
                longConverter,
                currencyBaseValues.getCurrencySign());
    }

    private Container(NumberToWordsConverter<Long> longConverter,
                      NumberToWordsConverter<BigDecimal> bigDecimalConverter) {
        //Inject attribute from outside
        this.longConverter = longConverter;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    /**
     * Quick create containers for some languages
     */
    public static Container polishContainer() {
        return new Container(new PolishCurrencyValues());
    }

    public static Container polishContainer(final String newCurrencySign) {
        return new Container(new PolishCurrencyValues(newCurrencySign));
    }

    public static Container russianContainer() {
        return new Container(new RussianCurrencyValues());
    }

    public static Container russianContainer(final String newCurrencySign) {
        return new Container(new RussianCurrencyValues(newCurrencySign));
    }

    public static Container czechContainer() {
        CzechCurrencyValues czechValues = new CzechCurrencyValues();
        CzechSmallCurrencyValues czechSmallCurrencyValues = new CzechSmallCurrencyValues();

        return getCzechContainer(czechValues, czechSmallCurrencyValues);
    }

    public static Container czechContainer(final String newCurrencySign) {
        CzechCurrencyValues czechValues = new CzechCurrencyValues(newCurrencySign);
        CzechSmallCurrencyValues czechSmallCurrencyValues = new CzechSmallCurrencyValues(newCurrencySign);

        return getCzechContainer(czechValues, czechSmallCurrencyValues);
    }

    public static Container englishContainer() {
        return new Container(new EnglishCurrencyValues());
    }

    public static Container englishContainer(final String newCurrencySign) {
        return new Container(new EnglishCurrencyValues(newCurrencySign));
    }

    public static Container germanContainer() {
        GermanCurrencyValues values = new GermanCurrencyValues();
        return getGermanContainer(values);
    }

    public static Container germanContainer(final String newCurrencySign) {
        GermanCurrencyValues values = new GermanCurrencyValues(newCurrencySign);
        return getGermanContainer(values);
    }

    public static Container brazilianPortugueseContainer() {
        BrazilianPortugueseCurrencyValues values = new BrazilianPortugueseCurrencyValues();
        return getBrazilianPortugueseContainer(values);
    }

    public static Container brazilianPortugueseContainer(final String overrideCurrencySign) {
        BrazilianPortugueseCurrencyValues values = new BrazilianPortugueseCurrencyValues(overrideCurrencySign);
        return getBrazilianPortugueseContainer(values);
    }

    public static Container vietnameseContainer() {
        return getVietnameseContainer(new VietnameseCurrencyValues());
    }

    public static Container vietnameseContainer(String overrideCurrencySign) {
        return getVietnameseContainer(new VietnameseCurrencyValues(overrideCurrencySign));
    }

    //Helpers
    private static Container getVietnameseContainer(VietnameseCurrencyValues values) {
        UnderThousandToWordsMapper underThousandToWordMapper = new VietnameseUnderThousandToWordsMapper(values.baseNumbers(),
                values.twoDigitsNumberSeparator());
        VietnameseLongToWordsConverter converter = new VietnameseLongToWordsConverter(underThousandToWordMapper, values.pluralForms());
        BDBankingMoneyToWordsConverter bigDecimalConverter = new BDBankingMoneyToWordsConverter(
                converter,
                values.getCurrencySign());

        return new Container(converter, bigDecimalConverter);
    }

    private static Container getCzechContainer(CzechCurrencyValues czechValues, CzechSmallCurrencyValues czechSmallValues) {
        Container containerForBigNumbers = new Container(czechValues);

        UnderThousandToWordsMapper mapper = new UnderThousandToWordsMapper(czechSmallValues.baseNumbers(),
                czechSmallValues.twoDigitsNumberSeparator());

        NumberProcessor processor = new NumberProcessor(containerForBigNumbers.getLongConverter(), new IntegerToWordsConverter(mapper, czechSmallValues.pluralForms()));
        CzechLongToWordsConverter czechConverter = new CzechLongToWordsConverter(processor, czechValues.exceptions());
        BDBankingMoneyToWordsConverter bigDecimalBankingMoneyValueConverter = new BDBankingMoneyToWordsConverter(
                czechConverter,
                czechValues.getCurrencySign());

        return new Container(czechConverter, bigDecimalBankingMoneyValueConverter);
    }

    private static Container getGermanContainer(GermanCurrencyValues values) {
        GermanThousandToWordsMapper mapper = new GermanThousandToWordsMapper(
                values.baseNumbers());

        NumberProcessor processor = new NumberProcessor(new LongToWordsConverter(mapper, values.pluralForms()),
                mapper);

        GermanLongToWordsConverter germanConverter = new GermanLongToWordsConverter(processor, values.exceptions());

        BDBankingMoneyToWordsConverter bigDecimalBankingMoneyValueConverter = new BDBankingMoneyToWordsConverter(
                germanConverter, values.getCurrencySign());

        return new Container(germanConverter, bigDecimalBankingMoneyValueConverter);
    }

    private static Container getBrazilianPortugueseContainer(BrazilianPortugueseCurrencyValues values) {
        PortugueseThousandToWordsMapper thousandToWordsMapper = new PortugueseThousandToWordsMapper(
                values.baseNumbers(), values.exceptions());

        NumberProcessor processor = new NumberProcessor(new PortugueseLongToWordsJoiner(thousandToWordsMapper, values.pluralForms()), thousandToWordsMapper);
        PortugueseLongToWordsConverter converter = new PortugueseLongToWordsConverter(processor, values.exceptions());

        BDBankingMoneyToWordsConverter bigDecimalBankingMoneyValueConverter = new BDBankingMoneyToWordsConverter(
                converter, values.getCurrencySign());

        return new Container(converter, bigDecimalBankingMoneyValueConverter);
    }

    /**
     * Getters/setters
     */
    public NumberToWordsConverter<Long> getLongConverter() {
        return longConverter;
    }

    public NumberToWordsConverter<BigDecimal> getBigDecimalConverter() {
        return bigDecimalConverter;
    }

    public BDBankingMoneyToWordsConverter getBankingMoneyConverter() {
        return (BDBankingMoneyToWordsConverter) bigDecimalConverter;
    }
}