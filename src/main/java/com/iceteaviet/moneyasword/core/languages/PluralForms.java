package com.iceteaviet.moneyasword.core.languages;

/**
 * Plural forms of languages
 *
 * Eg: thousand, hundred, nghìn, tỷ,..
 */
public interface PluralForms {

    String formFor(Integer value);

    GenderType genderType();
}
