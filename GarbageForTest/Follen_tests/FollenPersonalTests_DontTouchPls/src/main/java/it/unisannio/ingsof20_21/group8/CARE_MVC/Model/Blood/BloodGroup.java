package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public enum BloodGroup {
	Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg;
	
	
	private static  HashMap<BloodGroup, List<BloodGroup>> canDonateTo = new HashMap() {
		{
			put(Apos, new ArrayList<BloodGroup>(Arrays.asList(Apos, ABpos)));
			put(Aneg, new ArrayList<BloodGroup>(Arrays.asList(Apos, Aneg, ABpos, ABneg)));
			put(Bpos, new ArrayList<BloodGroup>(Arrays.asList(Bpos, ABpos)));
			put(Bneg, new ArrayList<BloodGroup>(Arrays.asList(Bpos, Bneg, ABpos, ABneg)));
			put(ZEROpos, new ArrayList<BloodGroup>(Arrays.asList(ZEROpos, Apos, Bneg, ABpos)));
			put(ZEROneg, new ArrayList<BloodGroup>(Arrays.asList(Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg)));
			put(ABpos, new ArrayList<BloodGroup>(Arrays.asList(ABpos)));
			put(ABneg, new ArrayList<BloodGroup>(Arrays.asList(ABpos, ABneg)));
		}
	};
	
	private static HashMap<BloodGroup, List<BloodGroup>> canReceiveFrom = new HashMap() {
		{
			put (Apos, new ArrayList<BloodGroup>(Arrays.asList(Apos, Aneg, ZEROpos, ZEROneg)));
			put (Aneg, new ArrayList<BloodGroup>(Arrays.asList(Aneg, ZEROneg)));
			put (Bpos, new ArrayList<BloodGroup>(Arrays.asList(Bpos, Bneg, ZEROpos, ZEROneg)));
			put (Bneg, new ArrayList<BloodGroup>(Arrays.asList(Bneg, ZEROneg)));
			put (ZEROpos, new ArrayList<BloodGroup>(Arrays.asList(ZEROpos, ZEROneg)));
			put (ZEROneg, new ArrayList<BloodGroup>(Arrays.asList(ZEROneg)));
			put (ABpos, new ArrayList<BloodGroup>(Arrays.asList(Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg)));
			put (ABneg, new ArrayList<BloodGroup>(Arrays.asList(ABneg, Aneg, Bneg, ZEROneg)));
		}
	};
	
	/* To preserve data we use Iterator to deny to remove items */
	public static Iterator<BloodGroup> canDonateTo(BloodGroup b){
		return canDonateTo.get(b).iterator();
	}
	
	public static Iterator<BloodGroup> canReceiveFrom(BloodGroup b){
		return canReceiveFrom.get(b).iterator();
	}
	
	public static String delimitedValues(String delimiter) {
		/*
		 * suggested delimitator: pipeline "|"		comma ","		semicolon ";"		tab "\t"		dash "-"	space " "
		 */
		return Arrays.stream( BloodGroup.values() )
				.map(Enum::toString)
                .collect(Collectors.joining( delimiter ));
	}
}