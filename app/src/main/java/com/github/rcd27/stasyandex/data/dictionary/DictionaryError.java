package com.github.rcd27.stasyandex.data.dictionary;

import com.google.gson.annotations.*;

//{"code":501,"message":"The specified language is not supported"}
public class DictionaryError {
  @SerializedName("code") public final String code;

  @SerializedName("message") public final String message;

  public DictionaryError(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
