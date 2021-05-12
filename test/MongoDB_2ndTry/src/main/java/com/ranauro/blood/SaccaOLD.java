package com.ranauro.blood;

public class SaccaOLD {

    private final Seriale seriale;
    private final BloodGroup gruppo;

    /*
     * @pre
     * gs not null
     */
    public SaccaOLD(BloodGroup gs) {
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
    public SaccaOLD(Seriale ser, BloodGroup gs) {
        assert gs != null && ser != null;
        seriale = ser;
        gruppo = gs;
    }

    public Seriale getSeriale() {
        return this.seriale;
    }
    public String getSerialeString(){
        return this.seriale.toString();
    }

    public BloodGroup getGruppo() {
        return this.gruppo;
    }
    public String getGruppoString(){
        return this.gruppo.toString();
    }

    public String toString() {
        return seriale + " - gruppo: " + gruppo;
    }

    public boolean equals(Object o) {
        return ((SaccaOLD) o).getSeriale().toString().equals(this.getSeriale().toString());
    }

}
