package rcd27.github.com.stasyandex;


import org.junit.Test;

import rcd27.github.com.stasyandex.fragments.dictionary.model.DictionaryAPI;
import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.DicResultDTO;
import rcd27.github.com.stasyandex.model.ApiModule;
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
