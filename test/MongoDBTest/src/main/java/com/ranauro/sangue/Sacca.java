package com.ranauro.sangue;

public class Sacca {
    private final Seriale seriale;
    private final String gruppo;

    /*
     * @pre
     * gs not null
     */
    public Sacca(String gs) {
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
    public Sacca(Seriale ser, String gs) {
        assert gs != null && ser != null;
        seriale = ser;
        gruppo = gs;
    }

    public Seriale getSeriale() {
        return this.seriale;
    }

    public String getGruppo() {
        return this.gruppo;
    }

    public String toString() {
        return seriale + " - gruppo: " + gruppo;
    }

    public boolean equals(Object o) {
        return ((Sacca) o).getSeriale().toString().equals(this.getSeriale().toString());
    }
}
