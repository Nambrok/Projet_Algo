import java.util.ArrayList;


public class Sommet {
	private String nom; //Nom du sommet.
	private ArrayList<Chemin> sorties; //Liste de toutes les arrêtes sortant de ce sommet.
	
	//Constructeur
	public Sommet(String nom){
		this.nom = nom;
		this.sorties = new ArrayList<Chemin>();
	}
	
	//Constructeur qui ajoute directement une arrêtes au sommet dés sa création.
	public Sommet(String nom, Chemin first){
		this.nom = nom;
		this.sorties = new ArrayList<Chemin>();
		this.sorties.add(first);
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
	
	public String getNom(){
		return this.nom;
	}
	
	public String toString(){
		String str = "";
		str += "Nom du sommet : " +this.getNom()+" | ";
		for(int i = 0; i<this.sorties.size(); i++){
			str+= this.sorties.get(i).toString();
		}
		return str;
	}
	
}
