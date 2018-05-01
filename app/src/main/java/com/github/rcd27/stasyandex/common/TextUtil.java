package com.github.rcd27.stasyandex.common;


import android.support.annotation.*;

import java.util.*;

public class TextUtil {

  private TextUtil() {
    // empty
  }

  public static String commaRawFromList(List<String> fromList) {
    if (fromList.size() == 1) {
      return fromList.get(0);
    } else {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < fromList.size(); i++) {
        if (i == fromList.size() - 1) {
          sb.append(fromList.get(i));
        } else {
          sb.append(fromList.get(i)).append(", ");
        }
      }
      return sb.toString();
    }
  }

  public static String findKeyByValue(@NonNull Map<String, String> languagesMap, String languageFrom) {
    for (Map.Entry<String, String> entry : languagesMap.entrySet()) {
      if (entry.getValue().equals(languageFrom)) {
        return entry.getKey();
      }
    }

    return "";
  }
}
