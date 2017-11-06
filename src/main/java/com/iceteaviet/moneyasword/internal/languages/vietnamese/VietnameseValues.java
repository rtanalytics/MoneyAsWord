package com.iceteaviet.moneyasword.internal.languages.vietnamese;

import com.iceteaviet.moneyasword.internal.BaseValues;
import com.iceteaviet.moneyasword.internal.languages.GenderForms;
import com.iceteaviet.moneyasword.internal.languages.PluralForms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.iceteaviet.moneyasword.internal.support.BaseNumbersBuilder.baseNumbersBuilder;

/**
 * Created by Genius Doan on 06/11/2017.
 */
public class VietnameseValues implements BaseValues {
    @Override
    public Map<Integer, GenderForms> baseNumbers() {
        return baseNumbersBuilder()
                .put(0, "không")
                .put(1, "một")
                .put(2, "hai")
                .put(3, "ba")
                .put(4, "bốn")
                .put(5, "năm")
                .put(6, "sáu")
                .put(7, "bảy")
                .put(8, "tám")
                .put(9, "chín")
                .put(10, "mười")
                .put(11, "mười một")
                .put(12, "mười hai")
                .put(13, "mười ba")
                .put(14, "mười bốn")
                .put(15, "mười lăm")
                .put(16, "mười sáu")
                .put(17, "mười bảy")
                .put(18, "mười tám")
                .put(19, "mười chín")
                .put(20, "hai mươi")
                .put(30, "ba mươi")
                .put(40, "bốn mươi")
                .put(50, "năm mươi")
                .put(60, "sáu mươi")
                .put(70, "bảy mươi")
                .put(80, "tám mươi")
                .put(90, "chín mươi")
                .put(100, "một trăm")
                .put(200, "hai trăm")
                .put(300, "ba trăm")
                .put(400, "bốn trăm")
                .put(500, "năm trăm")
                .put(600, "sáu trăm")
                .put(700, "bảy trăm")
                .put(800, "tám trăm")
                .put(900, "chín trăm")
                .build();
    }

    @Override
    public List<PluralForms> pluralForms() {
        return Arrays.asList(
                new VietnamesePluralForms(),
                new VietnamesePluralForms("nghìn"),
                new VietnamesePluralForms("triệu"),
                new VietnamesePluralForms("tỷ"));
    }

    @Override
    public String currency() {
        return "VNĐ";
    }

    @Override
    public char twoDigitsNumberSeparator() {
        return ' ';
    }
}
