package rcd27.github.com.stasyandex.model;


import org.junit.Before;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.model.dictionary.DictionaryAPI;
import rcd27.github.com.stasyandex.model.translation.TranslationAPI;

public class ModelImplTest {

    @Inject
    TranslationAPI translationAPI;

    @Inject
    DictionaryAPI dictionaryAPI;

    Model model;

    @Before
    public void setUp() throws Exception {

    }
}
