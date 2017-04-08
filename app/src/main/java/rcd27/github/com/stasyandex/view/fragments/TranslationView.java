package rcd27.github.com.stasyandex.view.fragments;


import java.util.List;

public interface TranslationView extends View {

    String getTranslationFor();

    void showTranslation(List<String> translationsToShow);

    void showEmpty();
}
