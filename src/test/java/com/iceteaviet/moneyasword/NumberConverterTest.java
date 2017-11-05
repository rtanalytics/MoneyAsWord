package com.iceteaviet.moneyasword;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Genius Doan on 05/11/2017.
 */
public class NumberConverterTest {
    @Test
    public void englishInteger() {
        ValueConverters converter = ValueConverters.ENGLISH_INTEGER;
        String valueAsWords = converter.asWords(1_234);

        assertEquals("one thousand two hundred thirty-four", valueAsWords);
    }
}
