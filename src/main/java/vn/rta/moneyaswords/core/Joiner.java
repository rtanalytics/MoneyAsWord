package vn.rta.moneyaswords.core;

import vn.rta.moneyaswords.core.languages.PluralForms;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Genius Doan on 07/11/2017.
 *
 * Join part of the generated text from number
 */
public interface Joiner<T, P extends PluralForms> {
    /**
     * Take the chunks, get the text represent of every digit using a mapper.
     * With every digit, get the corresponding currency values (thousand, hundred,..) base on the provided languages, plural forms.
     * Then convert it to the final text representation of the number.
     *
     * @param chunks parts of parsed the number
     * @param formsToUse
     * @return the final string result
     */
    String joinValueChunksWithForms(Iterator<T> chunks, Iterator<P> formsToUse);

    /**
     * Join result to the final string
     * Implement in converters, every converter has it's own implementation.
     * Usually get called after parse number to text and prepared the currency values.
     * @see Joiner#joinValueChunksWithForms(Iterator, Iterator)
     *
     * @param result parts of the generated text. Eg: ["one", "thousand", "two", "hundred",...]
     * @return the final string result -> "one thousand two hundred ..."
     */
    String joinParts(List<String> result);
}
