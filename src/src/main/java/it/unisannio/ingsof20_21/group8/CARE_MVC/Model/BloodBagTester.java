package it.unisannio.ingsof20_21.group8.CARE_MVC.Model;

public class BloodBagTester {

	public static void main(String[] args) {
		BloodBag bb = new BloodBag(Blood.Bpos);
		System.out.println( "Ho creato la sacca: "+ bb.getSerial() );
	}
}
