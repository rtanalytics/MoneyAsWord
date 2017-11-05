package com.iceteaviet.moneyasword.internal;

import com.iceteaviet.moneyasword.internal.languages.GenderType;

public interface GenderAwareIntegerToStringConverter {
    String asWords(Integer value, GenderType genderType);
}
