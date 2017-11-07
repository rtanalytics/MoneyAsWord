package com.iceteaviet.moneyasword.core;

import com.iceteaviet.moneyasword.core.languages.GenderType;

public interface GenderAwareIntegerToWordsMapper {
    String asWords(Integer value, GenderType genderType);
}
