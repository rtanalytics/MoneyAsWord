package vn.rta.moneyaswords.core.languages.portuguese;

import vn.rta.moneyaswords.core.NumberToWordsConverter;
import vn.rta.moneyaswords.core.converters.LongToWordsConverter;
import vn.rta.moneyaswords.core.languages.GenderType;
import vn.rta.moneyaswords.core.languages.PluralForms;
import vn.rta.moneyaswords.core.languages.shared.RegularPluralForms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PortugueseLongToWordsJoiner extends LongToWordsConverter<RegularPluralForms> {

    public PortugueseLongToWordsJoiner(NumberToWordsConverter<Integer> underThousandToWordMapper,
                                       List<RegularPluralForms> pluralForms) {
        super(underThousandToWordMapper, pluralForms);
    }

    @Override
    public String joinValueChunksWithForms(Iterator<Integer> chunks, Iterator<RegularPluralForms> formsToUse) {
        List<String> result = new ArrayList<>();

        while (chunks.hasNext() && formsToUse.hasNext()) {
            Integer currentChunkValue = chunks.next();
            PluralForms currentForms = formsToUse.next();

            if (currentChunkValue > 0) {
                result.add(underThousandToWordMapper.asWords(currentChunkValue, GenderType.GENDERLESS));
                result.add(currentForms.formFor(currentChunkValue));
            }
        }

        return joinParts(result);
    }
}