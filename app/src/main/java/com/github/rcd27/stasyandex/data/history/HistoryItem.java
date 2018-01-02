package com.github.rcd27.stasyandex.data.history;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryItem {
    @SerializedName("lang")
    @Expose
    private String lang;

    @SerializedName("textTo")
    @Expose
    private final String to;

    @SerializedName("textFrom")
    @Expose
    private final String from;

    @SerializedName("date")
    @Expose
    private final long date;

    @SerializedName("fav")
    @Expose
    private boolean isMarkedFav;

    public HistoryItem(String lang, String to, String from, long date, boolean markedFav) {
        this.lang = lang;
        this.to = to;
        this.from = from;
        this.date = date;
        this.isMarkedFav = markedFav;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTextFrom() {
        return from;
    }

    public String getTextTo() {
        return to;
    }

    public long getDate() {
        return date;
    }

    public boolean isMarkedFav() {
        return isMarkedFav;
    }

    public void setMarkedFav(boolean markedFav) {
        isMarkedFav = markedFav;
    }
}
