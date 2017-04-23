package rcd27.github.com.stasyandex;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.model.ApiModule;
import rcd27.github.com.stasyandex.model.translation.TranslationAPI;
import rcd27.github.com.stasyandex.presenter.visualobject.Translation;

import static org.junit.Assert.assertEquals;

public class MainTestCase {
    private TranslationAPI translationAPI;

    @Before
    public void getApi() {
        translationAPI = ApiModule.getTranslationApi();
    }

    @Test
    public void jsonMappigTest() {
        translationAPI.getAvailableLangs("ru").subscribe(System.out::print);
    }

    @Test
    public void translationShowTest() {
        List<String> fakeList = new ArrayList<>();
        fakeList.add("Раз перевод");
        Translation translation = new Translation(fakeList);
        assertEquals("Раз перевод", translation.show());

        fakeList.add("два перевод");
        fakeList.add("три перевод");
        translation = new Translation(fakeList);
        assertEquals("Раз перевод, два перевод, три перевод", translation.show());
    }
}
