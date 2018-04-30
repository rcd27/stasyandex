package com.github.rcd27.stasyandex.model.data.translation;

import com.google.gson.annotations.*;

import java.text.*;

/*
Получаемый DTO, Ответ API на команду detect.
 */
public class ProbableLanguage {
  @SerializedName("code") public final int code;
  @SerializedName("lang") public final String language;

  public ProbableLanguage(int code, String language) {
    this.code = code;
    this.language = language;
  }

  @Override
  public String toString() {
    return MessageFormat.format("Code: {0}, Probable language: {1}", code, language);
  }
}
