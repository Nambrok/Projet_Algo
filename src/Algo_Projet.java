
public class Algo_Projet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DomaineDeSki huez = new DomaineDeSki();
		huez.chargerDonnees("BD/BD.txt");
		//System.out.print(huez.toString());
		System.out.println(huez.getNombredeSommets());
		System.out.println(huez.afficherSommets());
		System.out.println(huez.plusCourtChemin_Djikstra("Signal", "Villard_Reculas"));

	}

}
