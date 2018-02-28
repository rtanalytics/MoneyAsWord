package vn.rta.moneyaswords.core.languages;

import java.util.List;
import java.util.Map;

/**
 * Base skeleton of currency values
 */
public interface CurrencyBaseValues extends CurrencySign {

    Map<Integer, GenderForms> baseNumbers();

    List<? extends PluralForms> pluralForms();

    char twoDigitsNumberSeparator();
}
