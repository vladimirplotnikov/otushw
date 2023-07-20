package com.github.vladimirplotnikov.json;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "chat_identifier",
        "last",
        "belong_number",
        "text"
})

public class OutChatSession implements Serializable
{

    @JsonProperty("chat_identifier")
    private String chatIdentifier;
    @JsonProperty("last")
    //private List<OutLast> outLast;
    private List<String> outLast;
    @JsonProperty("belong_number")
    private String belong_number;
    @JsonProperty("text")
    private List<OutTxt> outTxt;

    @JsonProperty("chat_identifier")
    public String getChatIdentifier() {
        return chatIdentifier;
    }

    @JsonProperty("chat_identifier")
    public void setChatIdentifier(String chatIdentifier) {
        this.chatIdentifier = chatIdentifier;
    }

    @JsonProperty("belong_number")
    public String getBelong_number() {
        return belong_number;
    }

    @JsonProperty("belong_number")
    public void setBelong_number(final String belong_number) {
        this.belong_number = belong_number;
    }

//    @JsonProperty("last")
//    public List<OutLast> getLast() {
//        return outLast;
//    }
//
//    @JsonProperty("last")
//    public void setLast(List<OutLast> outLast) {
//        this.outLast = outLast;
//    }


    public List<String> getOutLast() {
        return outLast;
    }

    public void setOutLast(List<String> outLast) {
        this.outLast = outLast;
    }

    @JsonProperty("text")
    public List<OutTxt> getTxt() {
        return outTxt;
    }

    @JsonProperty("text")
    public void setTxt(List<OutTxt> txt) {
        this.outTxt = txt;
    }

}