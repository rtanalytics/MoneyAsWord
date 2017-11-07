package com.iceteaviet.moneyasword.core.languages.english;

import com.iceteaviet.moneyasword.core.languages.GenderType;
import com.iceteaviet.moneyasword.core.languages.PluralForms;

public class EnglishPluralForms implements PluralForms {

    private final String form;

    public EnglishPluralForms() {
        this("");
    }

    public EnglishPluralForms(String form) {
        this.form = form;
    }

    @Override
    public String formFor(Integer value) {
        return form;
    }

    @Override
    public GenderType genderType() {
        return GenderType.GENDERLESS;
    }
}
