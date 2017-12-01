# MoneyAsWord
[![Coverage Status](https://coveralls.io/repos/github/nhoxbypass/MoneyAsWord/badge.svg?branch=master)](https://coveralls.io/github/nhoxbypass/MoneyAsWord?branch=master) [![Build Status](https://travis-ci.org/nhoxbypass/MoneyAsWord.svg?branch=master)](https://travis-ci.org/nhoxbypass/MoneyAsWord)

Simple library for convert number to words for currency cases.
Eg: 10.500 VND —> "Mười ngàn năm trăm đồng" in Vietnamese or "Ten thousand and five hundred Vietnamese dong" in English.

Features
--------

* Converts `Integer` to `String` with numerical representation.
* Converts `BigDecimal` to `String` with money representation and custom currency sign.

Supported languages
-------------------

* English
* Vietnamese
* Polish
* Czech
* Russian
* German
* Brazilian/Portuguese

Usage
-----

```java
//vietnameseBankingMoney
MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE);

assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn ₫", converter.asWords(new BigDecimal("105004000")));
assertEquals("một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn ₫", converter.asWords(new BigDecimal("1050040000")));
assertEquals("mười nghìn năm trăm ₫", converter.asWords(new BigDecimal("10500")));
assertEquals("một trăm mười nghìn hai trăm ₫", converter.asWords(new BigDecimal("110200")));
assertEquals("một tỷ một trăm hai mươi lăm triệu bốn trăm năm mươi mốt nghìn bảy trăm ₫", converter.asWords(new BigDecimal("1125451700")));
```


```java
//vietnameseBankingMoneyShowCurrencySignFirst
MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE);

assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn ₫", converter.asWords(new BigDecimal("105004000"), false));
assertEquals("₫ một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn", converter.asWords(new BigDecimal("1050040000"), true));
assertEquals("mười nghìn năm trăm ₫", converter.asWords(new BigDecimal("10500"), false));
assertEquals("một trăm mười nghìn hai trăm ₫", converter.asWords(new BigDecimal("110200")));
assertEquals("₫ một tỷ một trăm hai mươi ba triệu bốn trăm năm mươi sáu nghìn bảy trăm", converter.asWords(new BigDecimal("1123456700"), true));
```

```java
//vietnameseBankingMoneyOverrideCurrencySign
MoneyConverterManager converter = MoneyConverterManager.getConverterManager(MoneyConverterManager.VIETNAMESE, "Đô-la Mỹ");

assertEquals("một trăm lẻ năm triệu không trăm lẻ bốn nghìn Đô-la Mỹ", converter.asWords(new BigDecimal("105004000")));
assertEquals("một tỷ không trăm năm mươi triệu không trăm bốn mươi nghìn Đô-la Mỹ", converter.asWords(new BigDecimal("1050040000")));
assertEquals("mười nghìn năm trăm Đô-la Mỹ", converter.asWords(new BigDecimal("10500")));
assertEquals("một nghìn hai trăm ba mươi bốn Đô-la Mỹ 56/100", converter.asWords(new BigDecimal("1234.56")));
assertEquals("một tỷ một trăm hai mươi ba triệu bốn trăm năm mươi sáu nghìn bảy trăm Đô-la Mỹ", converter.asWords(new BigDecimal("1123456700")));
```
