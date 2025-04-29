package com.example.mailserver.utils;

import com.example.mailserver.model.Email;
import com.example.mailserver.model.Mailbox;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MailBoxStorage {
    private static final String STORAGE_DIRECTORY = "mailboxes/";

    private final Map<String, Mailbox> mailboxMap = new HashMap<>();

    public MailBoxStorage() {
        //Crea una nuova cartella se non esiste
        File dir = new File(STORAGE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //carica tutte le mailbox nella directory
        loadMailboxes();
    }

    //Aggiunge una mail alla mailbox del destinatario, se la mailbox non esiste viene ignorata
    public synchronized void deliverEmail(String recipient, Email email){
        Mailbox mailbox = mailboxMap.get(recipient);
        if (mailbox != null) {
            mailbox.addEmail(email);
            saveMailbox(mailbox);
        }
    }

    //Restituisce, se esiste, la mailbox di un utente
    public synchronized Mailbox getMailbox(String email){
        return mailboxMap.get(email);
    }

    //Verifica se un indirizzo email è gia registrato nel server
    public synchronized boolean emailExists(String email){
        return mailboxMap.containsKey(email);
    }

    //Carica tutte le mailbox dai file nella cartella di salvataggio
    private void loadMailboxes(){
        File[] files = new File(STORAGE_DIRECTORY).listFiles();
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                    Mailbox mailbox = (Mailbox) ois.readObject();
                    mailboxMap.put(mailbox.getEmailAddress(), mailbox);
                } catch (IOException | ClassNotFoundException e){
                    System.err.println("Errore nel caricamento della mailbox dal file: " + file.getName());
                }
            }
        }
    }

    //Salva su file la mailbox specificata
    public synchronized void saveMailbox(Mailbox mailbox){
        File file = new File(STORAGE_DIRECTORY + mailbox.getEmailAddress() + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(mailbox);
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio della mailbox: " + mailbox.getEmailAddress());
        }
    }

    public synchronized void createMailbox(String email){
        if(!emailExists(email)){
            Mailbox mailbox = new Mailbox(email);
            mailboxMap.put(email, mailbox);
            saveMailbox(mailbox);
        }
    }

}
