package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Location;

/*
 * 
 * @TODO: valutare di riempire una lista da un xml sia perché l'enum di una lista è poco elegante,
 * sia perché l'enum non consentirebbe all'utente di modificare agevolmente comuni accorpati o soppressi.
 * 
 */

public interface LocationEnum {

	public enum Country {
		Italy, Spain, Croatia, Greece;
	}
	
	public enum Region {
		Abruzzo, Basilicata, Bolzano, Calabria, Campania, Emilia_Romagna, Friuli_Venezia_Giulia, Lazio,	Liguria, Lombardia,	Marche,	Molise, Piemonte, Puglia, Sardegna, Sicilia, Toscana, Trento, Umbria, Valle_d_Aosta, Veneto;
	}
	
	public enum Province {
		Chieti, Potenza, Bolzano, Catanzaro, Napoli, Bologna, Udine, Roma, Genova, Varese, Ancona, Campobasso, Torino, Bari, Cagliari, Palermo, Firenze, Trento, Perugia, Aosta, Padova, Benevento, Avellino, Salerno, Caserta;
	}
	
	public enum City {
		Chieti, Potenza, Bolzano, Catanzaro, Napoli, Bologna, Udine, Roma, Genova, Varese, Ancona, Campobasso, Torino, Bari, Cagliari, Palermo, Firenze, Trento, Perugia, Aosta, Padova, Benevento, Avellino, Salerno, Caserta;
	}
}
