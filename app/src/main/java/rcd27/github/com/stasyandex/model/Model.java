package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.data.AvailableLanguages;
import rx.Observable;

public interface Model {

    Observable<AvailableLanguages> getAvailableLanguages(String forLanguage);

}
