package com.github.rcd27.stasyandex.model.business.translation;

import android.content.*;

import com.github.rcd27.airbag.*;
import com.github.rcd27.stasyandex.common.*;
import com.github.rcd27.stasyandex.model.data.history.*;
import com.github.rcd27.stasyandex.model.data.translation.*;
import com.github.rcd27.stasyandex.presentation.translation.*;
import com.google.gson.*;

public class TranslationInteractor {

  private final TranslationContract.Api api;
  private final Context context;

  public TranslationInteractor(TranslationContract.Api api, Context context) {
    this.api = api;
    this.context = context;
  }

  public ApiRequest<Translation> getTranslation(String text, String direction) {
    return new ApiRequest<>(api.getTranslation(text, direction));
  }

  public void saveCurrentTranslationToHistory(Translation current, String sourceText) {
    String to = TextUtil.commaRawFromList(current.getTranslationResult());

    HistoryItem historyItem = new HistoryItem(current.getDirection(), to, sourceText, 0, true);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(historyItem);

    // FIXME: move that to Interactor
    SharedPreferences prefs = context.getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();

    editor.putString(current.getDirection() + current.getTranslationResult().toString(), json);
    editor.apply();
  }
}
