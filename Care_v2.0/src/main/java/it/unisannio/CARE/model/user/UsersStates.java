package it.unisannio.CARE.model.user;

public class UsersStates {
    /**
     * 1: attivo
     *  0: disabilitato (quando sbaglia la password e l'admin deve riabilitarlo)
     *  -1: bloccato dal sistema: blacklist. può essere risbloccato dall'admin.
     *  -2: utente eliminato in modo logico
     *  */

    public static final short ACTIVE = (short) 1;
    public static final short INACTIVE = (short) 0;
    public static final short BLOCKED_BY_SYSTEM = (short) -1;
    public static final short DELETED = (short) -2;
}
