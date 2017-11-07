package com.iceteaviet.moneyasword.core.languages.german;

import com.google.common.collect.ImmutableMap;
import com.iceteaviet.moneyasword.core.languages.CurrencyBaseValues;
import com.iceteaviet.moneyasword.core.languages.GenderForms;
import com.iceteaviet.moneyasword.core.languages.GenderType;
import com.iceteaviet.moneyasword.core.languages.PluralForms;
import com.iceteaviet.moneyasword.core.languages.shared.RegularPluralForms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.iceteaviet.moneyasword.core.languages.GenderForms.genderForms;
import static com.iceteaviet.moneyasword.core.support.BaseNumbersBuilder.baseNumbersBuilder;

public class GermanCurrencyValues implements CurrencyBaseValues {

    @Override
    public Map<Integer, GenderForms> baseNumbers() {
        return baseNumbersBuilder()
                .put(0, "null")
                .put(1, genderForms("ein", "eine", "ein", "ein"))
                .put(2, "zwei")
                .put(3, "drei")
                .put(4, "vier")
                .put(5, "fünf")
                .put(6, "sechs")
                .put(7, "sieben")
                .put(8, "acht")
                .put(9, "neun")
                .put(10, "zehn")
                .put(11, "elf")
                .put(12, "zwölf")
                .put(13, "dreizehn")
                .put(14, "vierzehn")
                .put(15, "fünfzehn")
                .put(16, "sechzehn")
                .put(17, "siebzehn")
                .put(18, "achtzehn")
                .put(19, "neunzehn")
                .put(20, "zwanzig")
                .put(30, "dreißig")
                .put(40, "vierzig")
                .put(50, "fünfzig")
                .put(60, "sechzig")
                .put(70, "siebzig")
                .put(80, "achtzig")
                .put(90, "neunzig")
                .put(100, "einhundert")
                .put(200, "zweihundert")
                .put(300, "dreihundert")
                .put(400, "vierhundert")
                .put(500, "fünfhundert")
                .put(600, "sechshundert")
                .put(700, "siebenhundert")
                .put(800, "achthundert")
                .put(900, "neunhundert")
                .build();
    }

    public Map<Integer, String> exceptions() {
        return ImmutableMap.<Integer, String>builder().put(1, "eins").build();
    }

    @Override
    public List<PluralForms> pluralForms() {
        return Arrays.asList(
                new RegularPluralForms("Million", "Millionen", GenderType.FEMININE),
                new RegularPluralForms("Milliarde", "Milliarden", GenderType.FEMININE));
    }

    @Override
    public String getCurrencySign() {
        return "€";
    }

    @Override
    public char twoDigitsNumberSeparator() {
        return 0;
    }
}
