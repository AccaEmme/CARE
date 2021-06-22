package org.example.Model;

import org.bson.Document;

import java.util.List;

public class Provincia {
    List<Comune> comuni ;
    String nomeProvincia;
    String sigla;
    String id_regione;

    public Provincia(List<Comune> comuni, String nomeProvincia, String sigla, String id_regione){
        this.comuni = comuni;
        this.nomeProvincia = nomeProvincia;
        this.sigla = sigla;
        this.id_regione = id_regione;
    }

    public Document getDocument(){
        Document document = new Document();
            document.append("nomeProvincia",this.nomeProvincia);
            document.append("sigla",this.sigla);
            document.append("id_regione",this.id_regione);

        Document comuniDoc = new Document();
        comuniDoc.append("comuni",this.comuni);

        document.append("comuni",comuniDoc);
        return document;
    }

    public List<Comune> getComuni() {
        return comuni;
    }

    public void setComuni(List<Comune> comuni) {
        this.comuni = comuni;
    }

    public String getNomeProvincia() {
        return nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia) {
        this.nomeProvincia = nomeProvincia;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getId_regione() {
        return id_regione;
    }

    public void setId_regione(String id_regione) {
        this.id_regione = id_regione;
    }
}
