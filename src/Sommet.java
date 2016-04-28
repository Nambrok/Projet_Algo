import java.util.ArrayList;


public class Sommet {
	private String nom; //Nom du sommet.
	private ArrayList<Chemin> sorties; //Liste de toutes les arrêtes sortant de ce sommet.
	private ArrayList<Chemin> arrivant;
	
	private Chemin cheminArrivantPere; 
	private int distance;
	private boolean traiter;
	private Sommet pere;
	
	//Constructeur
	public Sommet(String nom){
		this.nom = nom;
		this.sorties = new ArrayList<Chemin>();
		this.arrivant = new ArrayList<Chemin>();
		this.setDistance(1000000000);
		this.setTraiterFalse();
		this.setPere(null);
		this.setCheminArrivantPere(null);
	}
	
	//Constructeur qui ajoute directement une arrêtes au sommet dés sa création.
	public Sommet(String nom, Chemin first){
		this.nom = nom;
		this.sorties = new ArrayList<Chemin>();
		this.arrivant = new ArrayList<Chemin>();
		this.sorties.add(first);
		this.setDistance(1000000000);
		this.setTraiterFalse();
		this.setPere(null);
		this.setCheminArrivantPere(null);
	}
	
	//Ajoute une arrête sortante à ce sommet.
	public void ajouterAretesSortantes(Chemin out){
		if(out.getDepart().equals(this.nom)){
			sorties.add(out);
		}
		else{
			System.out.println("Erreur : Chemin non ajoutée car il ne commence pas de ce sommet.");
		}
	}
	
	public void setDifficulteSortantes(int difficulte){
		for(Chemin c : this.sorties){
			c.setTailleFromDifficulte(difficulte);
		}
	}
	
	public void setDistance(int d){
		this.distance = d;
	}
	
	public int getDistance(){
		return this.distance;
	}
	
	public boolean isTraiter(){
		return this.traiter;
	}
	
	public void setTraiter(){
		this.traiter = true;
	}
	
	public void setTraiterFalse(){
		this.traiter = false;
	}
	
	public ArrayList<Chemin> getSortants(){
		return this.sorties;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public String toString(){
		String str = "";
		str += "Nom du sommet : " +this.getNom();
		str+= "\nEntrantes : ";
		for(Chemin c : this.arrivant){
			str += c.toString();
		}
		str+="\nSortantes : ";
		for(Chemin c : this.sorties){
			str += c.toString()+" ";
		}
		
		
		return str;
	}

	public Sommet getPere() {
		return pere;
	}

	public void setPere(Sommet pere) {
		this.pere = pere;
	}

	
	public void ajouterCheminArrivant(Chemin in){
		this.arrivant.add(in);
	}
	
	public ArrayList<Chemin> getArrivants(){
		return this.arrivant;
	}

	public Chemin getCheminArrivantPere() {
		return cheminArrivantPere;
	}

	public void setCheminArrivantPere(Chemin cheminArrivantPere) {
		this.cheminArrivantPere = cheminArrivantPere;
	}
}
