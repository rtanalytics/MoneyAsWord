package com.iceteaviet.moneyasword.internal.languages.czech;

import com.iceteaviet.moneyasword.internal.languages.CurrencyBaseValues;
import com.iceteaviet.moneyasword.internal.languages.GenderForms;
import com.iceteaviet.moneyasword.internal.languages.GenderType;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.iceteaviet.moneyasword.internal.languages.GenderForms.genderForm;

public class CzechValuesForSmallNumbersCurrency implements CurrencyBaseValues {

    private final CzechCurrencyValues originalValues = new CzechCurrencyValues();

    @Override
    public Map<Integer, GenderForms> baseNumbers() {
        Map<Integer, GenderForms> baseNumbers = originalValues.baseNumbers();
        baseNumbers.put(1, genderForm(baseNumbers.get(1).formFor(GenderType.FEMININE)));
        baseNumbers.put(2, genderForm(baseNumbers.get(2).formFor(GenderType.MASCULINE)));
        return baseNumbers;
    }

    @Override
    public List<PluralForms> pluralForms() {
        return Arrays.asList(new CzechPluralForms());
    }

    @Override
    public String getCurrencySign() {
        return originalValues.getCurrencySign();
    }

    @Override
    public char twoDigitsNumberSeparator() {
        return ' ';
    }
}