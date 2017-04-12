package rcd27.github.com.stasyandex.fragments.translation.presenter.vo;

/*
Просто объект для визуализации.
Сущность Translation потребуется вводить по мере добавления БД,
прикручивания всяких там флагов Любимое и проч.
 */

import java.io.Serializable;
import java.util.List;

public class Translation {
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
