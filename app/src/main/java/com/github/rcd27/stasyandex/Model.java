package com.github.rcd27.stasyandex;


import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;

import rx.Single;

public interface Model {

    Single<AvailableLanguages> getAvailableLanguages(String forLanguage);

    Single<ProbableLanguage> getProbableLanguage(String forText);

    Single<Translation> getTranslation(String forText, String direction);

    Single<DicResult> getDicResult(String forLanguage, String text, String forUi);
}
