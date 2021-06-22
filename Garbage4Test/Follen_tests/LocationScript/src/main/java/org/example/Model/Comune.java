package org.example.Model;

import org.bson.Document;

public class Comune {
    private String nomeComune;
    private String id_regione;
    private String id_provincia;
    public Comune(String nome, String id_regione, String id_provincia){
        this.nomeComune = nome;
        this.id_regione = id_regione;
        this.id_provincia = id_provincia;
    }

    public Document getDocument(){
        Document document = new Document();
            document.append("nome",this.nomeComune);
            document.append("id_regione",this.id_regione);
            document.append("id_provincia",this.id_provincia);

        return document;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public String getId_regione() {
        return id_regione;
    }

    public void setId_regione(String id_regione) {
        this.id_regione = id_regione;
    }

    public String getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(String id_provincia) {
        this.id_provincia = id_provincia;
    }
}
