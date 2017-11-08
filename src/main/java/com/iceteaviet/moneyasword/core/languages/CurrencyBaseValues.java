package com.iceteaviet.moneyasword.core.languages;

import java.util.List;
import java.util.Map;

public interface CurrencyBaseValues extends CurrencySign {

    Map<Integer, GenderForms> baseNumbers();

    List<? extends PluralForms> pluralForms();

    char twoDigitsNumberSeparator();
}
