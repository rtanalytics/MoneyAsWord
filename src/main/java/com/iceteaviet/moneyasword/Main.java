package com.iceteaviet.moneyasword;

import java.math.BigDecimal;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class Main {
    public static void main(String[] args) {
        IntegerConverterManager manager = IntegerConverterManager.getConverterManager(ConverterManager.VIETNAMESE);
        //System.out.println(manager.asWords(1050040));

        MoneyConverterManager m = MoneyConverterManager.getConverterManager(ConverterManager.VIETNAMESE, "Vietnamese dong");
        System.out.println(m.asWords(new BigDecimal("105004")));
    }
}
