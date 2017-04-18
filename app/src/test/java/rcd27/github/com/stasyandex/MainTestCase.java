package rcd27.github.com.stasyandex;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.presenter.visualobject.Translation;

import static org.junit.Assert.assertEquals;

public class MainTestCase {

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
