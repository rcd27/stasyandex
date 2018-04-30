package com.github.rcd27.stasyandex.data.dictionary;


import com.google.gson.annotations.*;

class Synonym {

  @SerializedName("text") public final String text;
  @SerializedName("pos") public final String pos;

  Synonym(String text, String pos) {
    this.text = text;
    this.pos = pos;
  }
}
