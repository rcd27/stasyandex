package rcd27.github.com.stasyandex;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.model.ApiModule;
import rcd27.github.com.stasyandex.model.dictionary.DictionaryAPI;
import rcd27.github.com.stasyandex.model.dictionary.dto.DicResult;
import rcd27.github.com.stasyandex.model.translation.TranslationAPI;
import rcd27.github.com.stasyandex.model.translation.dto.Translation;

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
                .toBlocking()
                .first();
        System.out.println(dicResult.getDefinition().toString());
        System.out.println(dicResult.getElementsList().toString());
    }
}
