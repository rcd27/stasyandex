package com.github.rcd27.stasyandex.presentation.dictionary.visual;


import java.text.*;

public class DictionaryVisualDefinition {

  public final String text;
  public final String pos;

  public DictionaryVisualDefinition(String text, String pos) {
    this.text = text;
    this.pos = pos;
  }

  @Override
  public String toString() {
    return MessageFormat.format("{0}\n({1})", text, pos);
  }
}
