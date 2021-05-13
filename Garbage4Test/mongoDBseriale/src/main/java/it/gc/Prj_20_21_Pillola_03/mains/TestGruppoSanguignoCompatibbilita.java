package it.gc.Prj_20_21_Pillola_03.mains;

import java.util.Iterator;

import it.gc.Prj_20_21_Pillola_03.sacca.GruppoSanguigno;

public class TestGruppoSanguignoCompatibbilita {

	/*
	 * stampa tabelle di compatibilità fra gruppi sanguigno
	 */
	public static void main(String[] args) {				
		for (GruppoSanguigno gs : GruppoSanguigno.values()) {
			System.out.println("Il gruppo: " + gs + " puo donare ai gruppi:");
			Iterator<GruppoSanguigno> itPuoDonareA = GruppoSanguigno.puoDonareA(gs);
			while(itPuoDonareA.hasNext())
				System.out.println("- " + itPuoDonareA.next());		
			System.out.println("e puo ricevere dai gruppi");
			Iterator<GruppoSanguigno> itPuoRicevereDa = GruppoSanguigno.puoRicevereDa(gs);
			while(itPuoRicevereDa.hasNext())
				System.out.println("- " + itPuoRicevereDa.next());		
		}
	}
}