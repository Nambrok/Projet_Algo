import java.util.Scanner;


public class Algo_Projet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialisation du graphe et chargement de la base de données.
		DomaineDeSki huez = new DomaineDeSki();
		huez.chargerDonnees("BD/BD.txt");
		String depart; String arriver;
		Scanner in = new Scanner(System.in);
		
		System.out.println(huez.afficherSommets());
		System.out.print("Entrer le point de départ : ");
		depart = in.nextLine();
		System.out.print("Entrer le point d'arriver : ");
		arriver = in.nextLine();
		System.out.println(huez.plusCourtChemin_Djikstra(depart, arriver));
		
//		System.out.println(huez.plusCourtChemin_Djikstra("Barbarate", "Barbarate"));
//		System.out.println(huez.plusCourtChemin_Djikstra("Signal", "Villard_Reculas"));
//		System.out.println(huez.plusCourtChemin_Djikstra("Signal", "Signal"));
//		System.out.println(huez.plusCourtChemin_Djikstra("Villard_Reculas", "Barbarate"));
	}

}
