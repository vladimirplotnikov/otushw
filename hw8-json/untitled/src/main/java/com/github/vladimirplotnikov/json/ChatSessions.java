package com.github.vladimirplotnikov.json;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "chat_sessions"
})

public class ChatSessions implements Serializable
{

    @JsonProperty("chat_sessions")
    private List<ChatSession> chatSessions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 2786904196814791485L;

    @JsonProperty("chat_sessions")
    public List<ChatSession> getChatSessions() {
        return chatSessions;
    }

    @JsonProperty("chat_sessions")
    public void setChatSessions(List<ChatSession> chatSessions) {
        this.chatSessions = chatSessions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}