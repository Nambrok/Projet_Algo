
public class Chemin {
	private String depart;
	private String arriver;
	private int taille;
	private String nom;
	private String type;
	//TODO: Il faut aussi ajouter la difficulté.
	
	//Constructeur.
	public Chemin(String nom, String type, String depart, String arriver, int taille){
		this.depart = depart;
		this.arriver = arriver;
		this.taille = taille;
		this.nom = nom;
		this.type = type;
	}
	
	public void setTailleFromDifficulte(int difficulte){
		if(difficulte == 0){
			//Normal, aucune taille ne change.
		}
		else{
			//experimenté
			if(this.type.equals("noir")){
				this.taille = this.taille * 4;
			}
			else if(this.type.equals("rouge")){
				this.taille = this.taille * 3;
			}
			else if(this.type.equals("bleu")){
				this.taille = this.taille * 2;
			}
		}
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
	
	public String getType(){
		return this.type;
	}
}
