package com.iceteaviet.moneyasword.core.languages.vietnamese;

import com.iceteaviet.moneyasword.core.languages.GenderType;
import com.iceteaviet.moneyasword.core.languages.PluralForms;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class VietnamesePluralForms implements PluralForms {
    private final String form;
    private final boolean isForcedKeep; //Flag to determine this number be shrink or not when the digit is 0

    public VietnamesePluralForms() {
        this("", false);
    }

    public VietnamesePluralForms(String form, boolean forcedKeep) {
        this.form = form;
        this.isForcedKeep = forcedKeep;
    }

    public boolean isForcedKeep() {
        return isForcedKeep;
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
