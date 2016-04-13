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
		String plusCourtChemin = "";
		
		ArrayList<String> marquer = new ArrayList<String>();
		ArrayList<String> aTraiter = new ArrayList<String>();
		for(Sommet s : sommets){
			aTraiter.add(s.getNom());
		}
		aTraiter.remove(depart);
		
		int d[] = new int[this.sommets.size()];
		int pere[] = new int[this.sommets.size()];
		String id[] = new String[this.sommets.size()];
		
		for(int i = 0; i<this.sommets.size(); i++){
			id[i] = this.sommets.get(i).getNom();
			this.sommets.get(i).setEmplacementTableau(i);
		}
		

		if(sommetExiste(depart)){//Je vérifie que le sommet duquel on veux commencer la recherche existe.
			marquer.add(depart);//Je met dans la pile le sommet de départ.
			for(int i = 0; i<this.sommets.size(); i++){//Dans cette boucle, j'initialise les sommets pour qu'ils soient tous considérés comme non traités. 
				d[i] = 10000000;
				pere[i] = -1;
				if(sommets.get(i).equals(arriver)){
					this.addArriver = i;
				}
				
			}
			//J'initialise le sommet de départ avec son temps à 0 et en ayant pas de père.
			for(int i = 0; i<this.sommets.size(); i++){
				if(sommets.get(i).getNom().equals(depart)){
					d[i] = 0;
					this.addDebut = i;
					pere[i] = -1;
				}
			}			
			//Affichage de tout les sommmets suivis de leur arrêtes sortantes.
//			for(Sommet s : sommets){
//				System.out.println("Début : "+ s.getNom());
//				for(Chemin c : s.getSortants()){
//					System.out.println("\t"+c.toString());
//				}
//				System.out.println("Sortie");
//			}
			
			while(aTraiter.size()>0){
				
				System.out.println(marquer.size()+ " Marquer : " + marquer.toString()+ "\n");
				System.out.println("aTraiter : " + aTraiter.toString()+ "\n-----------------------\n");
				int addSommet = _plusPetitNonTraiter(aTraiter, d, id);
				Sommet s = sommets.get(addSommet);
				for(Chemin c : s.getSortants()){
					for(int i = 0; i<this.sommets.size(); i++){
						if(sommets.get(i).getNom().equals(c.getArriver())){
							if(d[i]>c.getTaille()+d[addSommet]){
								d[i] = c.getTaille()+d[addSommet];
								pere[i] = s.getEmplacementTableau();
							}
						}
					}
				}
				marquer.add(s.getNom());
				aTraiter.remove(s.getNom());
//				for(int i = 0; i<this.sommets.size(); i++){
//					System.out.println("id : "+ id[i]+ " Pere : "+pere[i]+" Distance : "+d[i]);
//				}ça marche pas, il ne fait pas les calculs comme il faut.
			}
						
			//TODO: Ecrire tout le chemin le plus court en remontant depuis le sommet d'arrivée.
			//TODO: ça marche toujours pas !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			int target = this.addArriver;
//			while(pere[target] != -1){
//				plusCourtChemin += id[target]+" ";
//				System.out.println(target + " : "+id[target]+ " "+ pere[target] + " "+d[target]);
//				target = pere[target];
//			}
		}
		
		else{
			System.out.println("Erreur : Le sommet de départ spécifié n'existe pas.");
		}
		return plusCourtChemin;
	}
		
	private int _plusPetitNonTraiter(ArrayList<String> aTraiter, int d[], String id[]){
		int min = 100000000, minAdd = 0;
		for(int i = 0; i<this.sommets.size(); i++){
			for(String s : aTraiter){
				if(s.equals(id[i])){
					if(min > d[i]){
						min = d[i];
						minAdd = i;
					}
				}
			}
		}
		return minAdd;
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
