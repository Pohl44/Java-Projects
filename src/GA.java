import java.io.File;

public class GA {
	public static void main(String args[]){
		Daten.einlesen(new File("Inst2_10000.txt"));
		// Daten.ausgabe();
		
		int popSize             = 400;
		int anzahlIterationen   = 100;
		Individuum[] pop        = new Individuum[popSize];
		Individuum[] nachkommen = new Individuum[popSize];
		Individuum best         = new Individuum();
		
		///////////////////////////////////////////////
		best.initialisieren();
		best.fitnessBerechnen();
		for(int i=0;i<pop.length;i++){
			pop[i] = new Individuum();
			pop[i].initialisieren();
			pop[i].fitnessBerechnen();
		}
		bestIndividuumAktualisieren(pop, best);
		System.out.println(0 + " " + best.fitness);

		
		///////////////////////////////////////////////
		
		for(int iter=0; iter<anzahlIterationen; iter++){
			for(int i=0;i<nachkommen.length;i++){
				int elter1 = selektieren(pop);
				int elter2 = selektieren(pop);
				
				nachkommen[i] = new Individuum();


				nachkommen[i].crossover(pop[elter1], pop[elter2]);
				nachkommen[i].crossover(pop[elter2], pop[elter1]);
				
				nachkommen[i].mutieren();
				
				nachkommen[i].fitnessBerechnen();
			}
			
			pop        = nachkommen;
			nachkommen = new Individuum[popSize];
			bestIndividuumAktualisieren(pop, best);
			System.out.println(iter + " " + best.fitness);
		}
		best.ausgabe();
		
	}
	
	public static int selektieren(Individuum[] liste){
		int index = 0;
		
		//Wettkampf
		int index1 = (int)(Math.random()*liste.length);
		int index2 = (int)(Math.random()*liste.length);

		if(liste[index1].fitness > liste[index2].fitness){
			index = index1;
		}
		else{
			index = index2;
		}
		return index;
	}

	public static void bestIndividuumAktualisieren(Individuum[] liste, Individuum best){
		for(int i=0;i<liste.length;i++){
			if(liste[i].fitness > best.fitness){
				best.reproduzieren(liste[i]);
			}
		}
	}
	
}
