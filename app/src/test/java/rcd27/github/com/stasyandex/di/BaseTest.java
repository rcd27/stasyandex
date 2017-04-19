package rcd27.github.com.stasyandex.di;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rcd27.github.com.stasyandex.BuildConfig;

@RunWith(RobolectricTestRunner.class)
@Config(application = TestApplication.class,
        constants = BuildConfig.class,
        sdk = 25)
public class BaseTest {
    public TestComponent component;
//    public TestUtils testUtils;

    @Before
    public void setUp() throws Exception{
//        component = (TestComponent)
        //TODO ПРОДОЛЖАТЬ ОТСЮДА, ЭТО ПОЛНАЯ ЧУШЬ ПОВТОРЯТЬ ВСЁ КАК ТАМ
    }
    //
}
