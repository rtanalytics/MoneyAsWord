package com.iceteaviet.moneyasword.internal.languages.vietnamese;

import com.iceteaviet.moneyasword.internal.languages.GenderType;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class VietnamesePluralForms implements PluralForms {
    private final String form;

    public VietnamesePluralForms() {
        this("");
    }

    public VietnamesePluralForms(String form) {
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
