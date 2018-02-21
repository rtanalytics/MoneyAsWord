package com.iceteaviet.moneyasword;

import java.math.BigDecimal;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class Main {
    public static void main(String[] args) {
        MoneyConverterManager m = MoneyConverterManager.getConverterManager(BaseConverterManager.VIETNAMESE, "Vietnamese dong");
        System.out.println(m.asWords(new BigDecimal("2222222222222222")));
    }
}
