import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Daten {
	static int[] kunden;
	static int[] kosten;
	static int anzahlProjekte;
	static int budget;
	static double verhältnis[];
	
	public static void einlesen(File file){
		
		try{
			Scanner   	sc = new Scanner(file);
			anzahlProjekte = sc.nextInt();
			budget         = sc.nextInt();
			kunden         = new int[anzahlProjekte];
			kosten         = new int[anzahlProjekte];
			verhältnis	   = new double[anzahlProjekte];
			
			for(int i=0;i<anzahlProjekte;i++){
				kunden[i] = sc.nextInt();
				kosten[i] = sc.nextInt();
			}
			sort();
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage() + " --- ");
		}

	}


	public static void sort(){
		double k;
		int a, b;

		for (int i = 0; i<anzahlProjekte; i++) {

			verhältnis[i] = (kunden[i] / (double)kosten[i]);

			for (int j = anzahlProjekte-1; j > 0; j--){
				
				if (verhältnis[j-1] < verhältnis[j]) {
					k = verhältnis[j];
					a = kunden[j];
					b = kosten[j];

					verhältnis[j] = verhältnis[j-1];
					verhältnis[j-1] = k;
					kunden[j] = kunden[j-1];
					kunden[j-1] = a;
					kosten[j] = kosten[j-1];
					kosten[j-1] = b;
				}
			}
		}
	}

	public static void ausgabe(){
		System.out.println(anzahlProjekte);
		System.out.println(budget);

		for(int i=0;i<anzahlProjekte;i++){
			System.out.println(i + " " + kunden[i] + " " + kosten[i]);
		}
	}
}
