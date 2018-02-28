package vn.rta.moneyaswords.core.languages.czech;

import vn.rta.moneyaswords.core.NumberToWordsConverter;
import vn.rta.moneyaswords.core.support.NumberProcessor;

import java.util.Map;

public class CzechLongToWordsConverter implements NumberToWordsConverter<Long> {

    private final Map<Integer, String> exceptions;
    private final NumberProcessor numberProcessor;

    public CzechLongToWordsConverter(NumberProcessor numberProcessor, Map<Integer, String> exceptions) {
        this.exceptions = exceptions;
        this.numberProcessor = numberProcessor;
    }

    @Override
    public String asWords(Long value) {
        if (exceptions.containsKey(value)) {
            return exceptions.get(value);
        }

        Long bigNumber = value / 1000;
        Integer smallNumber = (int)(value % 1000);

        return numberProcessor.process(bigNumber, smallNumber);
    }
}
