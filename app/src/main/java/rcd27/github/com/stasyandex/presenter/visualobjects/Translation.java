package rcd27.github.com.stasyandex.presenter.visualobjects;

/*
Просто объект для визуализации.
Сущность Translation потребуется вводить по мере добавления БД,
прикручивания всяких там флагов Любимое и проч.
 */

import java.io.Serializable;

//TODO в туториале прикручен интерфейс Serializable. Зачем?
public class Translation implements Serializable{
    private final String translationResult;

    public Translation(String translationResult) {
        this.translationResult = translationResult;
    }

    public String getTranslationResult() {
        return translationResult;
    }
}
