package vn.rta.moneyaswords.core.languages.english;

import vn.rta.moneyaswords.core.languages.GenderType;
import vn.rta.moneyaswords.core.languages.PluralForms;

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
