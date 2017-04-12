package rcd27.github.com.stasyandex.fragments.translation.presenter.vo;

/*
Просто объект для визуализации.
Сущность Translation потребуется вводить по мере добавления БД,
прикручивания всяких там флагов Любимое и проч.
 */

import java.io.Serializable;
import java.util.List;

//TODO в туториале прикручен интерфейс Serializable. Зачем?
public class Translation implements Serializable {
    private final List<String> translationResult;

    public Translation(List<String> translationResult) {
        this.translationResult = translationResult;
    }

    public List<String> getTranslationResult() {
        return translationResult;
    }

    public boolean isEmpty() {
        return translationResult.isEmpty();
    }
}
