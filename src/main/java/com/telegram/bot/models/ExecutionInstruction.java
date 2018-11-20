package com.telegram.bot.models;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ExecutionInstruction {

    private Set<String> recipients;

    private Set<String> messages;

    private Integer timeReminding = 10000;

    public ExecutionInstruction() {
        initMessagesList();
    }

    public void setRecepients(String recipient) {
        if (recipients.isEmpty())
            recipients = new HashSet<String>();

        recipients.add(recipient);
    }

    public void setMessages(String message) {
        if (messages.isEmpty())
            messages = new HashSet<String>();

        messages.add(message);
    }

    public void setTimeReminding(Integer timeReminding) {
        if (timeReminding > 0)
            this.timeReminding = timeReminding;
    }

    private void initMessagesList() {

    }

    public boolean checkReadinessInstruction() {
        return !recipients.isEmpty();
    }
}
