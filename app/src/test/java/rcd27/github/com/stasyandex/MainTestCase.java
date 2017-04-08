package rcd27.github.com.stasyandex;


import com.google.gson.Gson;

import org.junit.Test;

import rcd27.github.com.stasyandex.model.api.ApiModule;
import rcd27.github.com.stasyandex.model.api.DictionaryAPI;
import rcd27.github.com.stasyandex.model.dto.dictionary.DicResultDTO;
import rx.Observable;

public class MainTestCase {
    @Test
    public void main() {
        DictionaryAPI dictionaryAPI = ApiModule.getDictionaryApi();
        Observable<DicResultDTO> resultFromServer = dictionaryAPI
                .getDicResultFor("ru-en", "привет", "ru");
        System.out.println(resultFromServer.isEmpty().toString());
    }
}
