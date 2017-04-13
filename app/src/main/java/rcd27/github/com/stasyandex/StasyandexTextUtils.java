package rcd27.github.com.stasyandex;


import java.util.List;

public class StasyandexTextUtils {
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
}
