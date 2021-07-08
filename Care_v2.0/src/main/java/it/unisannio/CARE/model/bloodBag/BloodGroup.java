package it.unisannio.CARE.model.bloodBag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Enumerator for the definition of the various types of blood groups and also of those compatible for donations
 */
public enum BloodGroup {
	Apos, Aneg, Bpos, Bneg, ZEROpos, ZEROneg, ABpos, ABneg;
	
	/**
	 **************************************************************************
	 * Private method for creating the HASHMAP with all kinds of donor bags
	 **************************************************************************
	 */
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
	
	/**
	 **************************************************************************
	 * Private method for creating the HASHMAP with all kinds of recipients' bags
	 **************************************************************************
	 */
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
	
	/**
	 **************************************************************************
	 * Method to preserve data and to deny the removal of elements from the donor
	 * @param b A BloodGroup object is passed
	 **************************************************************************
	 */
	public static Iterator<BloodGroup> canDonateTo(BloodGroup b){
		return canDonateTo.get(b).iterator();
	}
	
	/**
	 **************************************************************************
	 * Method to preserve data and to deny the removal of elements from the recipient
	 * @param b A BloodGroup object is passed
	 **************************************************************************
	 */
	public static Iterator<BloodGroup> canReceiveFrom(BloodGroup b){
		return canReceiveFrom.get(b).iterator();
	}
	
	/**
	 **************************************************************************
	 * Method that returns the string of all BloodGroup enumerator values separated by the delimiter specified as an argument
	 * @param delimiter indicates the delimiter to separate values
	 * @return Returns a string with all required values
	 **************************************************************************
	 */
	public static String delimitedValues(String delimiter) {
		/*
		 * suggested delimitator: pipeline "|"		comma ","		semicolon ";"		tab "\t"		dash "-"	space " "
		 */
		return Arrays.stream( BloodGroup.values() )
				.map(Enum::toString)
                .collect(Collectors.joining( delimiter ));
	}
}