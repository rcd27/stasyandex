package com.github.rcd27.stasyandex.data.history;


import com.google.gson.annotations.*;

public class HistoryItem {
  @SerializedName("lang") public final String lang;
  @SerializedName("textTo") public final String to;
  @SerializedName("textFrom") public final String from;
  @SerializedName("date") public final long date;
  @SerializedName("fav") public boolean isMarkedFav;

  public HistoryItem(
      String lang,
      String to,
      String from,
      long date,
      boolean isMarkedFav) {

    this.lang = lang;
    this.to = to;
    this.from = from;
    this.date = date;
    this.isMarkedFav = isMarkedFav;
  }

  public void setMarkedFav(boolean markedFav) {
    this.isMarkedFav = markedFav;
  }
}
