
public class Chemin {
	private String depart;
	private String arriver;
	private int taille;
	private String nom;
	private String type;
	//TODO: Il faut gérer les types et modifié la fonction qui lit la BD pour prendre en compte "Nom Type Départ Arriver Taille"
	//TODO: Il faut aussi ajouter la difficulté.
	
	//Constructeur d'avant l'ajout du type, rester pour l'instant car il manque la lecture du type
	//dans la fonction qui charge la DB.
	public Chemin(String nom, String depart, String arriver, int taille){
		this.depart = depart;
		this.arriver = arriver;
		this.taille = taille;
		this.nom = nom;
		this.type = "";
	}
	
	//Constructeur.
	public Chemin(String nom, String type, String depart, String arriver, int taille){
		this.depart = depart;
		this.arriver = arriver;
		this.taille = taille;
		this.nom = nom;
		this.type = type;
	}
	
	
	public String toString(){
		String str = new String();
		str += this.nom +" ";
		str += this.depart;
		str += "-->"+this.arriver+" ";
		str += "Temps : "+this.taille+" ";
		return str;
	}
	
	//Getter
	public String getDepart() {
		return this.depart;
	}
	
	public String getArriver(){
		return this.arriver;
	}
	
	public int getTaille(){
		return this.taille;
	}
	
	public String getNom(){
		return this.nom;
	}
}
