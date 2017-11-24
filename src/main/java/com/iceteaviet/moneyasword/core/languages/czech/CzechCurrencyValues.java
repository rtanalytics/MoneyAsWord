package com.iceteaviet.moneyasword.core.languages.czech;

import com.google.common.collect.ImmutableMap;
import com.iceteaviet.moneyasword.core.languages.CurrencyBaseValues;
import com.iceteaviet.moneyasword.core.languages.GenderForms;
import com.iceteaviet.moneyasword.core.languages.GenderType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.iceteaviet.moneyasword.core.languages.GenderForms.genderForms;
import static com.iceteaviet.moneyasword.core.support.BaseNumbersBuilder.baseNumbersBuilder;

public class CzechCurrencyValues implements CurrencyBaseValues {
    private String currencySign = "Kč";

    public CzechCurrencyValues() {

    }

    public CzechCurrencyValues(String currencySign) {
        this.currencySign = currencySign;
    }

    @Override
    public Map<Integer, GenderForms> baseNumbers() {
        return baseNumbersBuilder()
                .put(0, "nula")
                .put(1, genderForms("jeden", "jedna", "jedno"))
                .put(2, genderForms("dva", "dvě", "dvě"))
                .put(3, "tři")
                .put(4, "čtyři")
                .put(5, "pět")
                .put(6, "šest")
                .put(7, "sedm")
                .put(8, "osm")
                .put(9, "devět")
                .put(10, "deset")
                .put(11, "jedenáct")
                .put(12, "dvanáct")
                .put(13, "třináct")
                .put(14, "čtrnáct")
                .put(15, "patnáct")
                .put(16, "šestnáct")
                .put(17, "sedmnáct")
                .put(18, "osmnáct")
                .put(19, "devatenáct")
                .put(20, "dvacet")
                .put(30, "třicet")
                .put(40, "čtyřicet")
                .put(50, "padesát")
                .put(60, "šedesát")
                .put(70, "sedmdesát")
                .put(80, "osmdesát")
                .put(90, "devadesát")
                .put(100, "sto")
                .put(200, "dvě stě")
                .put(300, "tři sta")
                .put(400, "čtyři sta")
                .put(500, "pět set")
                .put(600, "šest set")
                .put(700, "sedm set")
                .put(800, "osm set")
                .put(900, "devět set")
                .build();
    }

    @Override
    public List<CzechPluralForms> pluralForms() {
        return Arrays.asList(
                new CzechPluralForms("tisíc", "tisíce", "tisíc", GenderType.MASCULINE),
                new CzechPluralForms("milion", "miliony", "milionů", GenderType.MASCULINE),
                new CzechPluralForms("miliarda", "miliardy", "miliard", GenderType.FEMININE));
    }

    @Override
    public String getCurrencySign() {
        return currencySign;
    }

    @Override
    public char twoDigitsNumberSeparator() {
        return ' ';
    }

    public Map<Integer, String> exceptions() {
        return ImmutableMap.<Integer, String>builder().put(2, "dvě").build();
    }
}
