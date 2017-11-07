package com.iceteaviet.moneyasword;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Genius Doan on 05/11/2017.
 */

public class MoneyConverterTest {
    @Test
    public void englishBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.ENGLISH);

        assertEquals("one thousand two hundred thirty-four £ 56/100", converter.asWords(new BigDecimal("1234.56")));
        assertEquals("eleven thousand two hundred thirty-four £", converter.asWords(new BigDecimal("11234")));
    }

    @Test
    public void englishBankingMoneyShowCurrencySignFirst() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.ENGLISH);

        assertEquals("one thousand two hundred thirty-four £ 56/100", converter.asWords(new BigDecimal("1234.56"), false));
        assertEquals("£ eleven thousand two hundred thirty-four 56/100", converter.asWords(new BigDecimal("11234.56"), true));
        assertEquals("eleven thousand two hundred thirty-four £", converter.asWords(new BigDecimal("11234"), false));
    }

    @Test
    public void englishBankingMoneyOverrideCurrencySign() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.ENGLISH, "Vietnamese dong");

        assertEquals("ten thousand five hundred Vietnamese dong", converter.asWords(new BigDecimal("10500")));
        assertEquals("one hundred ten thousand two hundred Vietnamese dong", converter.asWords(new BigDecimal("110200")));
    }


    @Test
    public void vietnameseBankingMoney() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE);

        assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn ₫", converter.asWords(new BigDecimal("105004000")));
        assertEquals("một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn ₫", converter.asWords(new BigDecimal("1050040000")));
        assertEquals("mười nghìn năm trăm ₫", converter.asWords(new BigDecimal("10500")));
        assertEquals("một trăm mười nghìn hai trăm ₫", converter.asWords(new BigDecimal("110200")));
        assertEquals("một tỷ một trăm hai mươi lăm triệu bốn trăm năm mươi mốt nghìn bảy trăm ₫", converter.asWords(new BigDecimal("1125451700")));
    }

    @Test
    public void vietnameseBankingMoneyShowCurrencySignFirst() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE);

        assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn ₫", converter.asWords(new BigDecimal("105004000"), false));
        assertEquals("₫ một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn", converter.asWords(new BigDecimal("1050040000"), true));
        assertEquals("mười nghìn năm trăm ₫", converter.asWords(new BigDecimal("10500"), false));
        assertEquals("một trăm mười nghìn hai trăm ₫", converter.asWords(new BigDecimal("110200")));
        assertEquals("₫ một tỷ một trăm hai mươi ba triệu bốn trăm năm mươi sáu nghìn bảy trăm", converter.asWords(new BigDecimal("1123456700"), true));
    }

    @Test
    public void vietnameseBankingMoneyOverrideCurrencySign() {
        MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE, "Đô-la Mỹ");

        assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn Đô-la Mỹ", converter.asWords(new BigDecimal("105004000")));
        assertEquals("một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn Đô-la Mỹ", converter.asWords(new BigDecimal("1050040000")));
        assertEquals("mười nghìn năm trăm Đô-la Mỹ", converter.asWords(new BigDecimal("10500")));
        assertEquals("một nghìn hai trăm ba mươi bốn Đô-la Mỹ 56/100", converter.asWords(new BigDecimal("1234.56")));
        assertEquals("một tỷ một trăm hai mươi ba triệu bốn trăm năm mươi sáu nghìn bảy trăm Đô-la Mỹ", converter.asWords(new BigDecimal("1123456700")));
    }
}
