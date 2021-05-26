package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;						// Sebbene noi abbiamo importato il package, la visibilità protected fa sì che non possiamo utilizzare quei metodi, ma solo instanziare l'oggetto. Potente!

public class BloodBagTester {

	public static void main(String[] args) throws ParseException {
		String CF1="BNCMRA86A41A509Y", CF2="RSSMRA80A01A509I";
		
		//BloodBag bb = new BloodBag(BloodGroup.Bpos, CF1);
		BloodBagInterface bbi = new BloodBag(BloodGroup.Bpos, CF1); 
		System.out.println( 
							"Ho creato la sacca: "			+ bbi.getSerial()
							+"\n con gruppo sanguigno "		+ bbi.getBloodType()
							+"\n con data creazione "		+ bbi.getCreationDate().toString()
							+"\n con data scadenza "		+ bbi.getExpirationDate()
							+"\n del paziente "				+ bbi.getDonatorCF()
						);
		/* Se opzionale il CF:
		System.out.println(
				bb.getDonatorCF()==null?"":("\n del donatore: "+bb.getDonatorID())
						);
		*/
		
		// -----------------------------------------------
		
		//BloodBag bb2 = new BloodBag(BloodGroup.ABneg, CF2);
		// Correttamente richiamando direttamente BloodBag non funziona perché protected, non nello stesso package.
		BloodBagInterface bb2 = new BloodBag(BloodGroup.ABneg, CF2);
		System.out.println( 
				"Ho creato la sacca: "			+ bb2.getSerial()
				+"\n con gruppo sanguigno "		+ bb2.getBloodType()
				+"\n con data creazione "		+ bb2.getCreationDate().toString()
				+"\n con data scadenza "		+ bb2.getExpirationDate()
				+"\n del paziente "				+ bb2.getDonatorCF()
			);
		/* Se opzionale il CF:
		System.out.println(
				bb2.getDonatorID()==null?"":("\n del donatore: "+bb2.getDonator())
			);
			*/
	}
}
