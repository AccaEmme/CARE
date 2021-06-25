package com.ranauro.sangue;

public class Sacca {

    private final Seriale seriale;
    private final GruppoSanguigno gruppo;

    /*
     * @pre
     * gs not null
     */
    public Sacca(GruppoSanguigno gs) {
        assert gs != null;
        seriale = new Seriale();
        gruppo = gs;
    }

    /*
     * @pre
     * ser not null
     * gs not null
     *
     */
    public Sacca(Seriale ser, GruppoSanguigno gs) {
        assert gs != null && ser != null;
        seriale = ser;
        gruppo = gs;
    }

    public Seriale getSeriale() {
        return this.seriale;
    }

    public GruppoSanguigno getGruppo() {
        return this.gruppo;
    }

    public String toString() {
        return seriale + " - gruppo: " + gruppo;
    }

    public boolean equals(Object o) {
        return ((Sacca) o).getSeriale().toString().equals(this.getSeriale().toString());
    }

}
