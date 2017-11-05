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
        MoneyConverterManager converter = MoneyConverterManager.ENGLISH_BANKING_MONEY_VALUE;
        String moneyAsWords = converter.asWords(new BigDecimal("1234.56"));

        assertEquals("one thousand two hundred thirty-four £ 56/100", moneyAsWords);
    }
}
