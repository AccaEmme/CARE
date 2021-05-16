package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.text.ParseException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;

public class BloodBagTester {

	public static void main(String[] args) throws ParseException {
		String CF1="BNCMRA86A41A509Y", CF2="RSSMRA80A01A509I";
		
		BloodBag bb = new BloodBag(Blood.Bpos, CF1);
		System.out.println( 
							"Ho creato la sacca: "			+ bb.getSerial()
							+"\n con gruppo sanguigno "		+ bb.getBloodType()
							+"\n con data creazione "		+ bb.getCreationDate().toString()
							+"\n con data scadenza "		+ bb.getExpirationDate()
							+"\n del paziente "				+ bb.getDonatorCF()
						);
		/* Se opzionale il CF:
		System.out.println(
				bb.getDonatorCF()==null?"":("\n del donatore: "+bb.getDonatorID())
						);
		*/
		
		// -----------------------------------------------
		
		BloodBag bb2 = new BloodBag(Blood.ABneg, CF2);
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
