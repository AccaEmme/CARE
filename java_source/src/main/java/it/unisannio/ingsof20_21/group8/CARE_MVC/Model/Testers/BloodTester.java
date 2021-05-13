package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;

import java.util.Iterator;

public class BloodTester {
	public static void main(String[] args) {
		
		System.out.println("TEST1: Print all");
		for(Blood b : Blood.values()) {
			System.out.print("\nIl gruppo "+b+"\n\tpuo' donare ai gruppi: ");
			Iterator<Blood> canDonateTo = Blood.canDonateTo(b);
			while(canDonateTo.hasNext())
				System.out.print("\t"+canDonateTo.next());
			
			System.out.print("\n\te puo' ricevere dai gruppi: ");
			Iterator<Blood> canReceiveFrom = Blood.canReceiveFrom(b);
			while(canReceiveFrom.hasNext())
				System.out.print("\t"+canReceiveFrom.next());
		}
		
		System.out.println("\n\n----\nTEST2: get specific receivables bloods");
		Blood bApos = Blood.Apos;
		System.out.println("Il gruppo sangugno "+bApos+" puo' ricevere da: ");
		Iterator<Blood> i = Blood.canReceiveFrom(bApos);
		while(i.hasNext())
			System.out.print("\t" + i.next());
		
	}
}
