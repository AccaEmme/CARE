package users;

public enum BloodBagState {
	Available,	// Sacca disponibile per essere usata o trasferita o eliminata
	Transfered,	// Sacca trasferita a un altro nodo, viene comunque lasciata l'informazione nel database del nodo trasferente nello stato "trasferita"
	Used,		// Sacca adoperata. Non utilizzabile, non trasferibile, non eliminabile
	Dropped;	// Sacca eliminata (es. per scadenza o altre motivazioni)
}
