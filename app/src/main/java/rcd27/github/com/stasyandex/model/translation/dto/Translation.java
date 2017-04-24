package rcd27.github.com.stasyandex.model.translation.dto;

import com.google.gson.annotations.SerializedName;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rcd27.github.com.stasyandex.TextUtil;

/*
Собственно перевод.
translationResult оформлен листом, но в нём замечен пока только один элемент.
 */
public class Translation {
    @SerializedName("code")
    private int code;

    @SerializedName("lang")
    private String direction;

    @SerializedName("text")
    private List<String> translationResult = new ArrayList<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<String> getTranslationResult() {
        return translationResult;
    }

    public void setTranslationResult(List<String> translationResult) {
        this.translationResult = translationResult;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Code: {0}, Direction: {1}, Translation result: {2}",
                code, direction, translationResult.toString());
    }

    public String show() {
        return TextUtil.commaRawFromList(translationResult);
    }

    public boolean isEmpty() {
        return getTranslationResult().isEmpty();
    }

    public static Map<String, String> createLanguagesMap() {
        HashMap<String, String> lang = new HashMap<>();
        lang.put("en", "английский");
        lang.put("ru", "русский");
        lang.put("az", "азербайджанский");
        lang.put("sq", "албанский");
        lang.put("am", "амхарский");
        lang.put("ar", "арабский");
        lang.put("hy", "армянский");
        lang.put("af", "африкаанс");
        lang.put("eu", "баскский");
        lang.put("ba", "башкирский");
        lang.put("be", "белорусский");
        lang.put("bn", "бенгальский");
        lang.put("bg", "болгарский");
        lang.put("bs", "боснийский");
        lang.put("cy", "валлийский");
        lang.put("hu", "венгерский");
        lang.put("vi", "вьетнамский");
        lang.put("ht", "гаитянский");
        lang.put("hl", "галисийский");
        lang.put("nl", "голландский");
        lang.put("mrj", "горномарийский");
        lang.put("el", "греческий");
        lang.put("ka", "грузинский");
        lang.put("gu", "гуджарати");
        lang.put("da", "датский");
        lang.put("he", "иврит");
        lang.put("yi", "идиш");
        lang.put("id", "индонезийский");
        lang.put("ga", "ирландский");
        lang.put("it", "итальянский");
        lang.put("is", "исландский");
        lang.put("es", "испанский");
        lang.put("kk", "казахский");
        lang.put("kn", "каннада");
        lang.put("ca", "каталанский");
        lang.put("ky", "киргизский");
        lang.put("zh", "китайский");
        lang.put("ko", "корейский");
        lang.put("xh", "коса");
        lang.put("la", "латынь");
        lang.put("lv", "латышский");
        lang.put("lt", "литовский");
        lang.put("lb", "люксембургский");
        lang.put("mg", "малагасийский");
        lang.put("ms", "малайский");
        lang.put("ml", "малаялам");
        lang.put("mt", "мальтийский");
        lang.put("mk", "македонский");
        lang.put("mi", "маори");
        lang.put("mhr", "марийский");
        lang.put("mn", "монгольский");
        lang.put("de", "немецкий");
        lang.put("ne", "непальский");
        lang.put("no", "норвежский");
        lang.put("pa", "панджаби");
        lang.put("pap", "папьяменто");
        lang.put("fa", "персидский");
        lang.put("pl", "польский");
        lang.put("pt", "португальский");
        lang.put("ro", "румынский");
        lang.put("seb", "себуанский");
        lang.put("sr", "сербский");
        lang.put("si", "сингальский");
        lang.put("sk", "словацкий");
        lang.put("sl", "словенский");
        lang.put("sw", "суахили");
        lang.put("su", "сунданский");
        lang.put("tg", "таджикский");
        lang.put("th", "тайский");
        lang.put("tl", "тагальский");
        lang.put("la", "тамильский");
        lang.put("tt", "татарский");
        lang.put("te", "телугу");
        lang.put("tr", "турецкий");
        lang.put("udm", "удмуртский");
        lang.put("uz", "узбекский");
        lang.put("uk", "украинский");
        lang.put("ur", "урду");
        lang.put("fi", "финский");
        lang.put("fr", "французский");
        lang.put("hi", "хинди");
        lang.put("hr", "хорватский");
        lang.put("cs", "чешский");
        lang.put("sv", "шведский");
        lang.put("gd", "шотландский");
        lang.put("et", "эстонский");
        lang.put("eo", "эсперанто");
        lang.put("jv", "яванский");
        lang.put("ja", "японский");
        return lang;
    }
}
