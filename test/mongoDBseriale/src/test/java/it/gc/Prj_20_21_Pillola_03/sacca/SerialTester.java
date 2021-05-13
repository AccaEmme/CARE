package it.gc.Prj_20_21_Pillola_03.sacca;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SerialTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Serial init_serial = new Serial("IT-NA206000-");
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Gruppo sanguigno [Apos, Aneg, Bpos, Bneg, ABpos, ABneg, ZEROpos, ZEROneg]:");
		String gsang = sc.nextLine();
		sc.close();
		
		Seriale s = new Seriale(GruppoSanguigno.valueOf(gsang) );
		System.out.println( s.toString() +"\n");
		
		//Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		//    public void run() {
		        //Serial.updateSerial();
		//    }
		//}));
	}

}