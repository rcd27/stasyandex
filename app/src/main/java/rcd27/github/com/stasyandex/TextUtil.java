package rcd27.github.com.stasyandex;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rcd27.github.com.stasyandex.model.dictionary.dto.MeaninigDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.SynonymDTO;

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
    public static String commaRawFromSynList(List<SynonymDTO> synonymDTOs) {
        List<String> synStrings = new ArrayList<>();
        for (SynonymDTO synonymDTO : synonymDTOs) {
            synStrings.add(synonymDTO.getText());
        }
        return commaRawFromList(synStrings);
    }

    public static String commaRawFromMeanList(List<MeaninigDTO> fromList) {
        List<String> resultList = new ArrayList<>();
        for (MeaninigDTO meaninigDTO : fromList) {
            resultList.add(meaninigDTO.getText());
        }
        return commaRawFromList(resultList);
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
