package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;						// Sebbene noi abbiamo importato il package, la visibilità protected fa sì che non possiamo utilizzare quei metodi, ma solo instanziare l'oggetto. Potente!
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

public class BloodBagTester {

	public static void main(String[] args) throws ParseException {
		String CF1="BNCMRA86A41A509Y", CF2="RSSMRA80A01A509I", CF3="MMCARN45B24A509I";
		
		Node n1 = new Node("206","Rummo",		new Location(Country.Italy, Region.Campania, Province.Benevento, City.Benevento, "via Roma", "44C"));
		Node n2 = new Node("204","Moscati",		new Location(Country.Italy, Region.Campania, Province.Avellino, City.Avellino, "via Europa", "33bis"));
		Node n3 = new Node("201","Cardarelli",	new Location(Country.Italy, Region.Campania, Province.Napoli, City.Napoli, "via dei Mille", "80/81"));
		
		//BloodBag bb = new BloodBag(BloodGroup.Bpos, CF1);
		BloodBagInterface bbi = new BloodBag(BloodGroup.Bpos, CF1, n1); 
		System.out.println( 
							"Ho creato la sacca: "			+ bbi.getSerial()
							+"\n con gruppo sanguigno "		+ bbi.getBloodGroup()
							+"\n con data creazione "		+ bbi.getCreationDate().toString()
							+"\n con data scadenza "		+ bbi.getExpirationDate()
							+"\n del paziente "				+ bbi.getDonatorCF()
							+"\n note: "					+ bbi.getNote()
							+"\n nel nodo: "				+ bbi.getNode().toString()
							+"\n\n"							+ bbi.toString()
						);
		
		// -----------------------------------------------
		
		//BloodBag bb2 = new BloodBag(BloodGroup.ABneg, CF2);
		// Correttamente richiamando direttamente BloodBag non funziona perché protected, non nello stesso package.
		BloodBagInterface bb2 = new BloodBag(BloodGroup.ABneg, CF2, n2);
		System.out.println( 
				"Ho creato la sacca: "			+ bb2.getSerial()
				+"\n con gruppo sanguigno "		+ bb2.getBloodGroup()
				+"\n con data creazione "		+ bb2.getCreationDate().toString()
				+"\n con data scadenza "		+ bb2.getExpirationDate()
				+"\n del paziente "				+ bb2.getDonatorCF()
				+"\n note: "					+ bb2.getNote()
				+"\n nel nodo: "				+ bb2.getNode().toString()
				+"\n\n"							+ bb2.toString()
			);
		/* Se opzionale il CF:
		System.out.println(
				bb2.getDonatorID()==null?"":("\n del donatore: "+bb2.getDonator())
			);
			*/
		
		BloodBag bb3 = new BloodBag(BloodGroup.Aneg, CF3, n3);
		System.out.println(
					"\n\nLa sacca "+bb3.getSerial()
					+" e' stata inserita nella struttura: "+bb3.getNode().getNodeName()
					+"("+bb3.getNode().getCodStr()+")"
					+" di "+bb3.getNode().getWarehouse().getCity()
				);
	}
}
