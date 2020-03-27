package com.example.mohammed_elagha.samplechatapp2;

public class Message {
    private String text;
    private String sender;
    private String receiver;

    public Message() {
    }

    public Message(String text, String sender, String receiver) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
