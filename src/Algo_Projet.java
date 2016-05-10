import java.util.Scanner;

public class Algo_Projet {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialisation du graphe et chargement de la base de données.
		
		
		DomaineDeSki huez = new DomaineDeSki(-1);
		huez.chargerDonnees("BD/Piste.txt");
		String depart; String arriver;
		
		Scanner in = new Scanner(System.in);
		
		
		while(huez.getDifficulte() == -1){
			System.out.print("Entrer la difficulté souhaité : (normal/experimente) ");
			huez.setDifficulte(in.nextLine());
		}
		/*
		System.out.println(huez.afficherSommets());
		System.out.print("Entrer le point de départ : ");
		depart = in.nextLine();
		System.out.print("Entrer le point d'arriver : ");
		arriver = in.nextLine();
		System.out.println(huez.plusCourtChemin_Djikstra(depart, arriver));
		*/
		
		for(Sommet s : huez.getListeSommets()){
			for(Sommet c : huez.getListeSommets()){
				System.out.println("Chemin le plus court entre " +s.getNom() +" et "+c.getNom());
				System.out.println(huez.plusCourtChemin_Djikstra(s.getNom(), c.getNom()));
				System.out.println('\n');
			}
		}//TODO: Trouver pourquoi certains sommets ne marche pas avec d'autres et marche avec certains.
		
		in.close();
	}
}
