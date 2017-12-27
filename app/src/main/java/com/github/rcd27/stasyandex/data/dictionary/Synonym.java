package com.github.rcd27.stasyandex.data.dictionary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Synonym {

    @SerializedName("text")
    @Expose
    String text;

    @SerializedName("pos")
    @Expose(deserialize = false)
    String pos;

}
