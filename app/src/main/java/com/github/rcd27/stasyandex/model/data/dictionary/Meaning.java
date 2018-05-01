package com.github.rcd27.stasyandex.model.data.dictionary;


import com.google.gson.annotations.*;

public class Meaning {

  @SerializedName("text") public final String text;

  public Meaning(String text) {
    this.text = text;
  }
}