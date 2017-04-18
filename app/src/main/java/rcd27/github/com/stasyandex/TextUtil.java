package rcd27.github.com.stasyandex;


import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.model.dictionary.Mean;
import rcd27.github.com.stasyandex.model.dictionary.Syn;

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

    //TODO запилить твой любимый дженерик.
    public static String commaRawFromSynList(List<Syn> syns) {
        List<String> synStrings = new ArrayList<>();
        for (Syn syn : syns) {
            synStrings.add(syn.getText());
        }
        return commaRawFromList(synStrings);
    }

    public static String commaRawFromMeanList(List<Mean> fromList) {
        List<String> resultList = new ArrayList<>();
        for (Mean mean : fromList) {
            resultList.add(mean.getText());
        }
        return commaRawFromList(resultList);
    }
}
