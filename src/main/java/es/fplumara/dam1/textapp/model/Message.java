package es.fplumara.dam1.textapp.model;

import java.time.LocalDateTime;

public class Message {

    private String texto;
    private LocalDateTime timestamp;
    private int numeropalabras;

    public  Message(String texto) {
        this.texto = texto;
        this.timestamp = LocalDateTime.now();
        String[] palabras = texto.trim().split("\\s+");
        numeropalabras = palabras.length;

    }

    public String getTexto() {
        return texto;
    }

    public int getNumeropalabras() {
        return numeropalabras;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
