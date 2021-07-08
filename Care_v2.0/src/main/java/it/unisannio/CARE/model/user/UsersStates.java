package it.unisannio.CARE.model.user;

public class UsersStates {
    /**
     * Class that holds the states of the user
      * 1: active
      * 0: disabled (when the password is wrong and the admin needs to re-enable it)
      * -1: blocked by system: blacklist. can be unlocked by admin.
      * -2: user logically deleted
     *  */

    public static final short ACTIVE 			= (short)  1;
    public static final short INACTIVE 			= (short)  0;
    public static final short BLOCKED_BY_SYSTEM = (short) -1;
    public static final short DELETED 			= (short) -2;	// necessary the last element.
}
