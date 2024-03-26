package com.example.porfirio.codicefiscale.model;

public class Citta {
    String nome;
    String codice;

    public Citta(String n, String c){
        nome=n;
        codice=c;
    }

    public String getNome() {
        return nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setNome(String n) {
        nome=n;
    }

    public void setCodice(String c) {
        codice=c;
    }

}

