package com.iceteaviet.moneyasword;

import java.io.Console;
import java.math.BigDecimal;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class Main {
    public static void main(String[] args) {
        ValueConverterManager manager = ValueConverterManager.getConverterManager(ConverterManager.VIETNAMESE);
        System.out.println(manager.asWords(1123));

        MoneyConverterManager m = MoneyConverterManager.getConverterManager(ConverterManager.VIETNAMESE);
        System.out.println(m.asWords(new BigDecimal("10500")));
    }
}
