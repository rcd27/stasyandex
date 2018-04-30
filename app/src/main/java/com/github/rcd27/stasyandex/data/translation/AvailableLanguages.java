package com.github.rcd27.stasyandex.data.translation;


import com.google.gson.annotations.*;

import java.text.*;
import java.util.*;

public class AvailableLanguages {

  @SerializedName("dirs") public final List<String> directions;
  @SerializedName("langs") public final Map<String, String> languages;

  public AvailableLanguages(List<String> directions, Map<String, String> languages) {
    this.directions = directions;
    this.languages = languages;
  }

  @Override
  public String toString() {
    return MessageFormat.format("Directions: {0}, Languages: {1}", directions, languages);
  }
}
