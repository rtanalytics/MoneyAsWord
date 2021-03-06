package vn.rta.moneyaswords.core.languages.vietnamese;

import vn.rta.moneyaswords.core.languages.GenderType;
import vn.rta.moneyaswords.core.languages.PluralForms;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class VietnamesePluralForms implements PluralForms {
    private final String form;

    /**
     * Flag to determine this number be shrink or not when the digit is 0
     */
    private final boolean isForcedKeep;

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
