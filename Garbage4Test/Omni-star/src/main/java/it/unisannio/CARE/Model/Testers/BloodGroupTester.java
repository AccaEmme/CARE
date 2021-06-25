package it.unisannio.CARE.Model.Testers;
import java.util.Iterator;

import it.unisannio.CARE.Model.Util.BloodGroup;

public class BloodGroupTester {
	public static void main(String[] args) {
		
		System.out.println("TEST1: Print all");
		for(BloodGroup b : BloodGroup.values()) {
			System.out.print("\nIl gruppo "+b+"\n\tpuo' donare ai gruppi: ");
			Iterator<BloodGroup> canDonateTo = BloodGroup.canDonateTo(b);
			while(canDonateTo.hasNext())
				System.out.print("\t"+canDonateTo.next());
			
			System.out.print("\n\te puo' ricevere dai gruppi: ");
			Iterator<BloodGroup> canReceiveFrom = BloodGroup.canReceiveFrom(b);
			while(canReceiveFrom.hasNext())
				System.out.print("\t"+canReceiveFrom.next());
		}
		
		System.out.println("\n\n----\nTEST2: get specific receivables bloods");
		BloodGroup bApos = BloodGroup.Apos;
		System.out.println("Il gruppo sangugno "+bApos+" puo' ricevere da: ");
		Iterator<BloodGroup> i = BloodGroup.canReceiveFrom(bApos);
		while(i.hasNext())
			System.out.print("\t" + i.next());
		
	}
}
