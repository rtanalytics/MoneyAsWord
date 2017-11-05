package com.iceteaviet.moneyasword.internal;

import com.iceteaviet.moneyasword.internal.languages.GenderForms;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;

import java.util.List;
import java.util.Map;

public interface BaseValues {

    Map<Integer, GenderForms> baseNumbers();

    List<PluralForms> pluralForms();

    String currency();

    char twoDigitsNumberSeparator();
}
