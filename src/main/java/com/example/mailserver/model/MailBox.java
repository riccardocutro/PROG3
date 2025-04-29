package com.example.mailserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailBox implements Serializable {
    private static final long serialVersionUID = 1L;

    // Indirizzo email associato alla mailbox
    private String emailAddress;

    // Lista dei messaggi di posta ricevuti dall'utente
    private List<Email> emails;


    //Costruttore che inizializza una Mailbox
    public MailBox(String emailAddress) {
        this.emailAddress = emailAddress;
        this.emails = new ArrayList<>();

    }

    //Restituisce l'indirizzo email associato alla mailbox.
    public String getEmailAddress() {
        return emailAddress;
    }

    //Restituisce la lista completa dei messaggi ricevuti.
    public List<Email> getEmails(){
        return emails;
    }


    //Aggiunge un nuovo messaggio email alla casella di posta.
    public void addEmail(Email email){
        this.emails.add(email);
    }


    //Rimuove un messaggio dalla casella identificato dal suo ID.
    public void removeEmail(String id){
        emailsremoveIf(e -> e.getId().equals(id));
    }

    // Restituisce l'elenco dei messaggi ricevuti dopo una certa data e ora.
    public List<Email> getNewEmails(LocalDateTime lastFetchTime){
        List<Email> newEmails = new ArrayList<>();
        for(Email e : emails){
            if(e.getTimestamp().isAfter(lastFetchTime)){
                newEmails.add(e);
            }
        }
        return newEmails;
    }

    //Rappresentazione testuale della mailbox
    @Override
    public String toString() {
        return "MailBox [emailAddress=" + emailAddress + ", with=" + emails.size() + "messages.]";
    }
}