package it.unisannio.ingsof20_21.group8.CARE_MVC.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public enum Blood {
	Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg;
	
	
	private static  HashMap<Blood, List<Blood>> canDonateTo = new HashMap<>() {
		{
			put(Apos, new ArrayList<Blood>(Arrays.asList(Apos, ABpos)));
			put(Aneg, new ArrayList<Blood>(Arrays.asList(Apos, Aneg, ABpos, ABneg)));
			put(Bpos, new ArrayList<Blood>(Arrays.asList(Bpos, ABpos)));
			put(Bneg, new ArrayList<Blood>(Arrays.asList(Bpos, Bneg, ABpos, ABneg)));
			put(ZEROpos, new ArrayList<Blood>(Arrays.asList(ZEROpos, Apos, Bneg, ABpos)));
			put(ZEROneg, new ArrayList<Blood>(Arrays.asList(Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg)));
			put(ABpos, new ArrayList<Blood>(Arrays.asList(ABpos)));
			put(ABneg, new ArrayList<Blood>(Arrays.asList(ABpos, ABneg)));
		}
	};
	
	private static HashMap<Blood, List<Blood>> canReceiveFrom = new HashMap<>() {
		{
			put (Apos, new ArrayList<Blood>(Arrays.asList(Apos, Aneg, ZEROpos, ZEROneg)));
			put (Aneg, new ArrayList<Blood>(Arrays.asList(Aneg, ZEROneg)));
			put (Bpos, new ArrayList<Blood>(Arrays.asList(Bpos, Bneg, ZEROpos, ZEROneg)));
			put (Bneg, new ArrayList<Blood>(Arrays.asList(Bneg, ZEROneg)));
			put (ZEROpos, new ArrayList<Blood>(Arrays.asList(ZEROpos, ZEROneg)));
			put (ZEROneg, new ArrayList<Blood>(Arrays.asList(ZEROneg)));
			put (ABpos, new ArrayList<Blood>(Arrays.asList(Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg)));
			put (ABneg, new ArrayList<Blood>(Arrays.asList(ABneg, Aneg, Bneg, ZEROneg)));
		}
	};
	
	/* To preserve data we use Iterator to deny to remove items */
	public static Iterator<Blood> canDonateTo(Blood b){
		return canDonateTo.get(b).iterator();
	}
	
	public static Iterator<Blood> canReceiveFrom(Blood b){
		return canReceiveFrom.get(b).iterator();
	}
}