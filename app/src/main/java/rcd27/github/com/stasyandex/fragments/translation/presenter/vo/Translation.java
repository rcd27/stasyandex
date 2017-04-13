package rcd27.github.com.stasyandex.fragments.translation.presenter.vo;

/*
Просто объект для визуализации.
Сущность Translation потребуется вводить по мере добавления БД,
прикручивания всяких там флагов Любимое и проч.
 */

import java.util.List;

public class Translation {
    private final List<String> translationResult;

    public Translation(List<String> translationResult) {
        this.translationResult = translationResult;
    }

    public boolean isEmpty() {
        return translationResult.isEmpty();
    }

    public String show() {
        if (translationResult.size() == 1) {
            return translationResult.get(0);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < translationResult.size(); i++) {
                if (i == translationResult.size() - 1) {
                    sb.append(translationResult.get(i));
                } else {
                    sb.append(translationResult.get(i)).append(", ");
                }
            }
            return sb.toString();
        }
    }
}
