package vn.rta.moneyaswords;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Genius Doan on 05/11/2017.
 */

public class MoneyConverterTest {
    @Test
    public void englishBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.ENGLISH);

        assertEquals("one thousand two hundred thirty-four £ 56/100", converter.asWords(new BigDecimal("1234.56")));
        assertEquals("eleven thousand two hundred thirty-four £", converter.asWords(new BigDecimal("11234")));
    }

    @Test
    public void englishBankingMoneyShowCurrencySignFirst() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.ENGLISH);

        assertEquals("one thousand two hundred thirty-four £ 56/100", converter.asWords(new BigDecimal("1234.56"), false));
        assertEquals("£ eleven thousand two hundred thirty-four 56/100", converter.asWords(new BigDecimal("11234.56"), true));
        assertEquals("eleven thousand two hundred thirty-four £", converter.asWords(new BigDecimal("11234"), false));
    }

    @Test
    public void englishBankingMoneyOverrideCurrencySign() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.ENGLISH, "Vietnamese dong");

        assertEquals("ten thousand five hundred Vietnamese dong", converter.asWords(new BigDecimal("10500")));
        assertEquals("one hundred ten thousand two hundred Vietnamese dong", converter.asWords(new BigDecimal("110200")));
    }


    @Test
    public void vietnameseBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.VIETNAMESE);

        assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn ₫", converter.asWords(new BigDecimal("105004000")));
        assertEquals("một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn ₫", converter.asWords(new BigDecimal("1050040000")));
        assertEquals("mười nghìn năm trăm ₫", converter.asWords(new BigDecimal("10500")));
        assertEquals("một trăm mười nghìn hai trăm ₫", converter.asWords(new BigDecimal("110200")));
        assertEquals("một tỷ một trăm hai mươi lăm triệu bốn trăm năm mươi mốt nghìn bảy trăm ₫", converter.asWords(new BigDecimal("1125451700")));
        assertEquals("chín nghìn tỷ ₫", converter.asWords(new BigDecimal("9000000000000")));
        assertEquals("chín nghìn tỷ ₫", converter.asWords(new BigDecimal("9000000000000")));
    }

    @Test
    public void vietnameseBankingMoneyShowCurrencySignFirst() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.VIETNAMESE);

        assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn ₫", converter.asWords(new BigDecimal("105004000"), false));
        assertEquals("₫ một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn", converter.asWords(new BigDecimal("1050040000"), true));
        assertEquals("mười nghìn năm trăm ₫", converter.asWords(new BigDecimal("10500"), false));
        assertEquals("một trăm mười nghìn hai trăm ₫", converter.asWords(new BigDecimal("110200")));
        assertEquals("₫ một tỷ một trăm hai mươi ba triệu bốn trăm năm mươi sáu nghìn bảy trăm", converter.asWords(new BigDecimal("1123456700"), true));
    }

    @Test
    public void vietnameseBankingMoneyOverrideCurrencySign() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.VIETNAMESE, "Đô-la Mỹ");

        assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn Đô-la Mỹ", converter.asWords(new BigDecimal("105004000")));
        assertEquals("một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn Đô-la Mỹ", converter.asWords(new BigDecimal("1050040000")));
        assertEquals("mười nghìn năm trăm Đô-la Mỹ", converter.asWords(new BigDecimal("10500")));
        assertEquals("một nghìn hai trăm ba mươi bốn Đô-la Mỹ 56/100", converter.asWords(new BigDecimal("1234.56")));
        assertEquals("một tỷ một trăm hai mươi ba triệu bốn trăm năm mươi sáu nghìn bảy trăm Đô-la Mỹ", converter.asWords(new BigDecimal("1123456700")));
        assertEquals("hai tỷ hai trăm hai mươi hai triệu hai trăm hai mươi hai nghìn hai trăm hai mươi hai Đô-la Mỹ", converter.asWords(new BigDecimal("2222222222")));
        assertEquals("một nghìn không trăm lẻ một tỷ Đô-la Mỹ", converter.asWords(new BigDecimal("1001000000000")));
    }

    @Test
    public void brazilianBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.BRAZILIAN_PORTUGUESE);

        assertEquals("mil duzentos e trinta e quatro R$ 56/100", converter.asWords(new BigDecimal("1234.56")));
    }

    @Test
    public void germanBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.GERMAN);

        assertEquals("eintausendzweihundertvierunddreißig € 56/100", converter.asWords(new BigDecimal("1234.56")));
    }

    @Test
    public void russianBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.RUSSIAN);

        assertEquals("одна тысяча двести тридцать четыре руб. 56/100", converter.asWords(new BigDecimal("1234.56")));
    }

    @Test
    public void polishBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(BaseConverterManager.POLISH);

        assertEquals("jeden tysiąc dwieście trzydzieści cztery PLN 56/100", converter.asWords(new BigDecimal("1234.56")));
    }

    @Test
    public void czechBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.CZECH);

        assertEquals("jeden tisíc dvě stě třicet čtyři Kč 56/100", converter.asWords(new BigDecimal("1234.56")));
    }

    @Test(expected = Exception.class)
    public void nullGiven() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE);

        converter.asWords(null);
    }
}
