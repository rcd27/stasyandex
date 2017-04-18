package rcd27.github.com.stasyandex.presenter.visualobject;

/*
Просто объект для визуализации.
Сущность Translation потребуется вводить по мере добавления БД,
прикручивания всяких там флагов Любимое и проч.
 */

import java.util.List;

import rcd27.github.com.stasyandex.TextUtil;

public class Translation {
    private final List<String> translationResult;

    public Translation(List<String> translationResult) {
        this.translationResult = translationResult;
    }

    public boolean isEmpty() {
        return translationResult.isEmpty();
    }

    public String show() {
        return TextUtil.commaRawFromList(translationResult);
    }
}
