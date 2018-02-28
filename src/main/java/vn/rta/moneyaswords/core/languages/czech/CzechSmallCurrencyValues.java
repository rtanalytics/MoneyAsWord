package vn.rta.moneyaswords.core.languages.czech;

import vn.rta.moneyaswords.core.languages.CurrencyBaseValues;
import vn.rta.moneyaswords.core.languages.GenderForms;
import vn.rta.moneyaswords.core.languages.GenderType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CzechSmallCurrencyValues implements CurrencyBaseValues {
    private CzechCurrencyValues originalValues;

    public CzechSmallCurrencyValues() {
        originalValues = new CzechCurrencyValues();
    }

    public CzechSmallCurrencyValues(String currencySign) {
        originalValues = new CzechCurrencyValues(currencySign);
    }

    @Override
    public Map<Integer, GenderForms> baseNumbers() {
        Map<Integer, GenderForms> baseNumbers = originalValues.baseNumbers();
        baseNumbers.put(1, GenderForms.genderForm(baseNumbers.get(1).formFor(GenderType.FEMININE)));
        baseNumbers.put(2, GenderForms.genderForm(baseNumbers.get(2).formFor(GenderType.MASCULINE)));
        return baseNumbers;
    }

    @Override
    public List<CzechPluralForms> pluralForms() {
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
