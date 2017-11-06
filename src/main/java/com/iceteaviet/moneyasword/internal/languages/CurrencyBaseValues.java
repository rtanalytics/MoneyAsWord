package com.iceteaviet.moneyasword.internal.languages;

import com.iceteaviet.moneyasword.internal.CurrencySign;

import java.util.List;
import java.util.Map;

public interface CurrencyBaseValues extends CurrencySign {

    Map<Integer, GenderForms> baseNumbers();

    List<PluralForms> pluralForms();

    char twoDigitsNumberSeparator();
}
