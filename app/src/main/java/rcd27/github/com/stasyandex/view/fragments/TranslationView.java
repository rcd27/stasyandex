package rcd27.github.com.stasyandex.view.fragments;


import java.util.List;

import rcd27.github.com.stasyandex.model.data.Translation;

public interface TranslationView extends View {

    String getTranslationFor();

    void showTranslation(List<Translation> translationsToShow);

}
