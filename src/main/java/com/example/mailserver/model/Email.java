package com.example.mailserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    //id del messaggio
    private String id;

    //indirizzo del mittente
    private String sender;

    //Lista degli indirizzi destinatari
    private List<String> recipient;

    //Oggetto del messaggio
    private String subject;

    //Corpo del messaggio
    private String body;

    //Data e ora di spedizione della mail
    private LocalDateTime timestamp;

    public Email(String id, String sender, List<String> recipient, String subject, String body, LocalDateTime timestamp) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getSender() {
        return sender;
    }

    public List<String> getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "From: " + sender + "\n" +
                " To: " + recipient.toString() + "\n" +
                " Subject: " + subject + "\n" +
                " Date: " + timestamp + "\n\n" +
                body;
    }

}
