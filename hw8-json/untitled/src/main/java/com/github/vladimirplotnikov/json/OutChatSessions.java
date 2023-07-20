package com.github.vladimirplotnikov.json;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "chat_sessions"
})

public class OutChatSessions implements Serializable
{

    @JsonProperty("chat_sessions")
    private List<OutChatSession> outChatSessions;

    public List<OutChatSession> getOutChatSessions() {
        return outChatSessions;
    }

    public void setOutChatSessions(List<OutChatSession> outChatSessions) {
        this.outChatSessions = outChatSessions;
    }
}