package rcd27.github.com.stasyandex.di;

import dagger.Component;
import rcd27.github.com.stasyandex.model.ModelImplTest;

@Component(modules = {ModelTestModule.class})
public interface TestComponent {

    void inject(ModelImplTest modelImplTest);
}
