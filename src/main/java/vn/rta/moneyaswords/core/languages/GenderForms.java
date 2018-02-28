package vn.rta.moneyaswords.core.languages;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static java.lang.String.format;

/**
 * Gender forms of currency values
 *
 * Some languages in the word they use different words for each gender. Eg: actor/actress.
 */
public class GenderForms {

    private final Map<GenderType, String> forms;

    public GenderForms(String masculineForm, String feminineForm, String neuterForm) {
        this.forms = ImmutableMap.<GenderType, String>builder()
                .put(GenderType.MASCULINE, masculineForm)
                .put(GenderType.FEMININE, feminineForm)
                .put(GenderType.NEUTER, neuterForm)
                .build();
    }

    public GenderForms(String masculineForm, String feminineForm, String neuterForm, String nonApplicableForm) {
        this.forms = ImmutableMap.<GenderType, String>builder()
                .put(GenderType.MASCULINE, masculineForm)
                .put(GenderType.FEMININE, feminineForm)
                .put(GenderType.NEUTER, neuterForm)
                .put(GenderType.GENDERLESS, nonApplicableForm)
                .build();
    }

    public GenderForms(String nonApplicableForm) {
        this.forms = ImmutableMap.<GenderType, String>builder()
                .put(GenderType.MASCULINE, nonApplicableForm)
                .put(GenderType.FEMININE, nonApplicableForm)
                .put(GenderType.NEUTER, nonApplicableForm)
                .put(GenderType.GENDERLESS, nonApplicableForm)
                .build();
    }

    public static GenderForms genderForms(String masculineForm, String feminineForm, String neuterForm) {
        return new GenderForms(masculineForm, feminineForm, neuterForm);
    }

    public static GenderForms genderForms(String masculineForm, String feminineForm, String neuterForm, String nonApplicableForm) {
        return new GenderForms(masculineForm, feminineForm, neuterForm, nonApplicableForm);
    }

    public static GenderForms genderForm(String nonApplicableForm) {
        return new GenderForms(nonApplicableForm);
    }

    public String formFor(GenderType gender) {
        validate(gender);
        return forms.get(gender);
    }

    private void validate(GenderType gender) {
        if (!forms.containsKey(gender)) {
            throw new MissingFormException(format("No form found for %s. Available forms: %s", gender, forms));
        }
    }
}
