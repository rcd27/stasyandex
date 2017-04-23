package rcd27.github.com.stasyandex;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class TextUtil {
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

    public static String findKeyByValue(Map<String, String> languagesMap, String languageFrom) {
        for (Map.Entry<String, String> entry : languagesMap.entrySet()) {
            if (entry.getValue().equals(languageFrom)) {
                return entry.getKey();
            }
        }
        return "";
    }
}
