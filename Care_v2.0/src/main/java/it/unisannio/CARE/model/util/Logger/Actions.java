package it.unisannio.CARE.model.util.Logger;

/**
 * possible log actions
 */
public enum Actions {
    //user actions
    USER_LOGIN,
    USER_LOGOUT,
    USER_PASSWORD_CHANGE,
    USER_EMAIL_CHANGE,
    USER_USERNAME_CHANGE,

    //blood bag actions
    BLOODBAG_USED,
    BLOODBAG_ADDED,
    BLOODBAG_TRANSFERED,
    BLOODBAG_ARRIVED,
    BLOODBAG_DROPPED,
    BLOODBAG_REQUESTED,
    BLOODBAG_SEND_CENTRAL,
    BLOODBAG_IMPORT,
    BLOODBAG_GET,
    BLOODBAG_ADD
}
