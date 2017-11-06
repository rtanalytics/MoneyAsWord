package com.iceteaviet.moneyasword.internal.languages.shared;

import com.iceteaviet.moneyasword.internal.languages.GenderType;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;

public class RegularPluralForms implements PluralForms {

    private final String singularForm;
    private final String pluralForm;
    private final GenderType genderType;

    public RegularPluralForms(String singularForm, String pluralForm, GenderType genderType) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
        this.genderType = genderType;
    }

    public RegularPluralForms(String singularForm, String pluralForm) {
        this(singularForm, pluralForm, GenderType.GENDERLESS);
    }

    @Override
    public String formFor(Integer value) {
        return (value == 1) ? singularForm : pluralForm;
    }

    @Override
    public GenderType genderType() {
        return genderType;
    }
}
