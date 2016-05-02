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
		
		System.out.println(huez.afficherSommets());
		System.out.print("Entrer le point de départ : ");
		depart = in.nextLine();
		System.out.print("Entrer le point d'arriver : ");
		arriver = in.nextLine();
		System.out.println(huez.plusCourtChemin_Djikstra(depart, arriver));
		
		in.close();
	}
}
