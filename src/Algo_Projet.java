
public class Algo_Projet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		DomaineDeSki huez = new DomaineDeSki();
		huez.chargerDonnees("BD/BD.txt");
		System.out.println(huez.toString());

	}

}
