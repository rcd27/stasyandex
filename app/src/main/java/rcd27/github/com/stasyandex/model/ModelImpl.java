package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.api.ApiInterface;
import rcd27.github.com.stasyandex.model.api.ApiModule;
import rcd27.github.com.stasyandex.model.data.AvailableLanguages;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Observable<AvailableLanguages> getAvailableLanguages(String forLanguage) {
        return apiInterface.getAvailableLangs(forLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
