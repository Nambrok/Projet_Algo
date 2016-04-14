import java.io.*;
import java.util.ArrayList;


public class DomaineDeSki {
	private ArrayList<Sommet> sommets;
	private int addDebut;
	private int addArriver;
	//Constructeur.
	public DomaineDeSki(){
		this.sommets = new ArrayList<Sommet>();
		this.addDebut = 0;
		this.addArriver = 0;
	}
	
	public int getNombredeSommets(){
		//Renvoie le nombre de sommet que contient le graphe.
		return this.sommets.size();
	}
	
	private boolean sommetExiste(String d){
		//Renvoie true si le sommet d existe dans le graphe et false sinon.
		for(Sommet s : sommets){
			if(s.getNom().equals(d)){
				return true;
			}
		}
		return false;
	}
	
	public String plusCourtChemin_Djikstra(String depart, String arriver){
		ArrayList<Sommet> aTraiter = new ArrayList<Sommet>();
		//Affichage de test Affiche les sommets suivies de leurs pistes sortantes.
		for(Sommet s : sommets){
			System.out.print(s.getNom()+" ");
			for(Chemin c : s.getSortants()){
				System.out.print(c.getNom()+" ");
			}
			System.out.println();
		}
		
		if(sommetExiste(depart)){
			System.out.println("Sommet " +depart+" existe.");
			for(Sommet s : sommets){
				if(s.getNom().equals(depart)){
					s.setDistance(0);
					s.setPere(null);
					aTraiter.add(s);
				}
			}
			System.out.println("Sommet de départ initialisés.");
			int i = 0;
			while(_traitementTerminer()){
				System.out.println("Traitement "+(i++));
				Sommet enTraitement = _plusPetitNonTraiter(aTraiter);
				System.out.println(enTraitement.getNom());
				for(Chemin c : enTraitement.getSortants()){
					System.out.println(c.getNom());
				}
			}
		}
		else{
			System.out.println("Erreur : Le sommet de départ spécifié n'existe pas.");
		}
		return "Hello World!";
	}
		
	private boolean _traitementTerminer() {
		for(Sommet s : sommets){
			if(!(s.isTraiter())){
				return false;
			}
		}
		return true;
	}

	private Sommet _plusPetitNonTraiter(ArrayList<Sommet> aTraiter){
		Sommet min = null;
		for(Sommet s : aTraiter){
			if(!(s.isTraiter())){
				min = s;
			}
		}
		if(min == null){
			return null;
		}
		for(Sommet s : aTraiter){
			if(s.getDistance() < min.getDistance()){
				min = s;
			}
		}
		
		return min;
	}
	public void chargerDonnees(String nomFile){
		try{
			BufferedReader read = new BufferedReader(new FileReader(nomFile));
			String in = read.readLine();
			while(in != null){
				_CreationPisteFromString(in);//Je lis la DB ligne par ligne et ensuite en appelant
				in = read.readLine();//_CreationPisteFromString() j'ajoute les sommets non connu et connecte
			}						//les pistes au sommets déjà connus.
			read.close();
			
		}
		catch(IOException err){
			System.out.println("Exception : "+ err);
			System.out.println("Problème rencontrée lors de la lecture de la base de données.");
		}
	}
	
	private void _CreationPisteFromString(String in){
		//Je traite ici la chaine de caractères obtenue en entrée pour en extraire les informations nécessaires
		//à la création des pistes et sommets du graphe.
		//TODO: Apprendre à lire le type depuis la DB c'est comme "nom type depart arriver taille"
		String nom = ""; String depart = ""; String arriver = ""; String StrTaille = ""; int taille = 0;
		int tres = 0;
		for(int i = 0; i<in.length(); i++){
			if(tres == 0){
				if(in.charAt(i) != ' '){
					nom += in.charAt(i);				
				}
				else{
					tres++;
				}
			}
			else if(tres == 1){
				if(in.charAt(i) != ' '){
					depart += in.charAt(i);				
				}
				else{
					tres++;
				}
			}
			else if(tres == 2){
				if(in.charAt(i) != ' '){
					arriver += in.charAt(i);				
				}
				else{
					tres++;
				}
			}
			else if(tres == 3){
				if(in.charAt(i) != ' '){
					StrTaille += in.charAt(i);
				}
				else{
					tres++;
				}
			}
		}
		taille = Integer.parseInt(StrTaille);
		linkCheminASommet(new Chemin(nom, depart, arriver, taille));
		
//		this.pistes.add(new Chemin(nom, depart, arriver, taille));
	}
	
	public void linkCheminASommet(Chemin aLinker){
		//C'est la fonction qui s'occupe de donner les pistes(Chemin ou arrêtes) aux sommets et si
		//le sommet n'existe pas de le créer.
		boolean existe = false;
		for(Sommet s : sommets){
			if(s.getNom().equals(aLinker.getDepart())){
				s.ajouterAretesSortantes(aLinker);
				existe = true;
			}
		}
		if(!existe){
			sommets.add(new Sommet(aLinker.getDepart(), aLinker));
		}
		
		existe = false;
		for(Sommet s : sommets){
			if(s.getNom().equals(aLinker.getArriver())){
				existe = true;
			}
		}
		if(!existe){
			sommets.add(new Sommet(aLinker.getArriver()));
		}
	}
	
	public String toString(){
		//Fonction qui renvoie les Sommets qui compose ce graphe.
		String str = "";
		for(int i = 0; i<this.getNombredeSommets(); i++){
			str+= this.sommets.get(i).toString()+"\n";
		}
		 
		return str;
	}
	
	public String afficherSommets(){
		//Fonction à part de toString() qui ne renvoie que les nom des sommets du graphe.
		String str = "";
		str+="----------------------\n";
		for(Sommet s : sommets){
			str+= s.getNom()+"\n----------------------\n";
		}
		
		return str;
	}
}
