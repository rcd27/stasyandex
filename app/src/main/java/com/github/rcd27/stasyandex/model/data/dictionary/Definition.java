package com.github.rcd27.stasyandex.model.data.dictionary;

import android.support.annotation.*;

import com.google.gson.annotations.*;

import java.util.*;

/*Словарная статья*/
public class Definition {
  @SerializedName("text") public final String text;

  /*Часть речи, может отсутствовать*/
  @Nullable
  @SerializedName("pos")
  public final String pos;

  @SerializedName("anm") public final String anm;

  /*Массив переводов*/
  @SerializedName("tr") public final List<DicTranslation> dicTranslation;

  public Definition(
      String text,
      String pos,
      String anm,
      List<DicTranslation> dicTranslation) {

    this.text = text;
    this.pos = pos;
    this.anm = anm;
    this.dicTranslation = dicTranslation;
  }
}
