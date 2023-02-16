
public class Individuum {
	int[] bits;
	int   fitness;
	
	public Individuum(){
		bits = new int[Daten.anzahlProjekte];
	}
	
	public void crossover(Individuum papa, Individuum mama){
		
		//int trennstelle = (int)(Math.random()*bits.length);
		int trennstelle = (bits.length / 2);
		
		//Kind 1
		for(int i=0;i<trennstelle;i++){
			bits[i] = papa.bits[i];
		}
		for(int i=trennstelle;i<bits.length;i++){
			bits[i] = mama.bits[i];
		}


	}

	
	
	public void fitnessBerechnen(){
	//Anpassung eines GA an das Problem
		int tempBudget = Daten.budget;
		fitness = 0;
		for(int i=0;i<bits.length;i++){
			if (bits[i] == 1 && tempBudget >= Daten.kosten[i]) {
				tempBudget -= Daten.kosten[i];

				fitness += Daten.kunden[i];
			}
		}
	}

	public void mutieren() {
		int zahl1 = (int)(Math.random()*bits.length);
		int zahl2 = (int)(Math.random()*bits.length);

		int temp = bits[zahl1];

		bits[zahl1] = bits[zahl2];
		bits[zahl2] = temp;
		
	}
	
	public void initialisieren(){

		 double[] verhältnis = new double[bits.length];

		for(int i=0;i<bits.length;i++){

			verhältnis[i] = (Daten.kunden[i] / Daten.kosten[i]);


			bits[i] = 0;
			if(Math.random()>0.5){
				bits[i] = 1;
			}
		}
	}
	public void ausgabe(){
		// for(int i=0;i<bits.length;i++){
		// 	System.out.print(bits[i]);
		// }
		System.out.print(" " + fitness);
		System.out.println();
	}

	public void reproduzieren(Individuum vorlage){
		for(int i=0;i<bits.length;i++){
			this.bits[i] = vorlage.bits[i];
		}
		this.fitness = vorlage.fitness;
	}
	
}
