package com.github.rcd27.stasyandex.model;


public interface Const {
    String UI_THREAD = "UI_THREAD";
    String IO_THREAD = "IO_THREAD";

    String TRANSLATE_URL = "https://translate.yandex.net/";
    String TRANSLATE_API_KEY
            = "trnsl.1.1.20170329T205120Z.3a93ae67f64429e6" +
            ".6d650fe0829ed1f0a269d9e3f850da537b87cc4b";
    String TRANSLATE_UI = "ru";

    String DICTIONARY_URL = "https://dictionary.yandex.net/";
    String DICTIONARY_API_KEY = "dict.1.1.20170408T212234Z." +
            "478c26abd6ca258f.8ada3c887a5c6a9c35bcfe919dab24faf9ffb732";
    String Dictionary_UI = "ru";

    int DIRECTION_FROM = 1;
    int DIRECTION_TO = 2;

    String TRANSLATION_CACHE = "TRANSLATION_CACHE";
    String HISTORY_CACHE = "HISTORY_CACHE";
}
