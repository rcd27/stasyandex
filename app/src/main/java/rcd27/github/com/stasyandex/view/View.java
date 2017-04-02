package rcd27.github.com.stasyandex.view;


import rcd27.github.com.stasyandex.model.data.AvailableLanguages;

public interface View {

    void showAvailableLanguages(AvailableLanguages availableLanguages);

    void showError(String error);

    void showEmptyAvaialableLanguages();

    String forLanguage();

}
