package com.github.rcd27.stasyandex.data.dictionary;

import com.google.gson.annotations.SerializedName;

//{"code":501,"message":"The specified language is not supported"}
public class DictionaryError {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public DictionaryError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
