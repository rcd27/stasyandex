package com.github.rcd27.stasyandex;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.github.rcd27.stasyandex.dictionary.DictionaryAPI;
import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.net.ApiModule;
import com.github.rcd27.stasyandex.translation.TranslationAPI;
import com.github.rcd27.stasyandex.data.translation.Translation;
import com.github.rcd27.stasyandex.dictionary.DictionaryVisualItem;

import static org.junit.Assert.assertEquals;

public class MainTestCase {
    private TranslationAPI translationAPI;
    private DictionaryAPI dictionaryAPI;

    @Before
    public void getApi() {
        translationAPI = ApiModule.getTranslationApi();
        dictionaryAPI = ApiModule.getDictionaryApi();
    }

    @Test
    public void translationShowTest() {
        List<String> fakeList = new ArrayList<>();
        fakeList.add("Раз перевод");
        Translation translation = new Translation();
        translation.setTranslationResult(fakeList);
        assertEquals("Раз перевод", translation.show());

        fakeList.add("два перевод");
        fakeList.add("три перевод");
        assertEquals("Раз перевод, два перевод, три перевод", translation.show());
    }

    @Test
    public void dicResultMapperTest() {
        DicResult dicResult = dictionaryAPI.getDicResultFor("ru-en", "художник", "ru")
                .toBlocking().last();
        System.out.println(dicResult.getDefinition().toString());
        for (DictionaryVisualItem dvi : dicResult.getElementsList()) {
            System.out.println(dvi);
        }
    }
}
