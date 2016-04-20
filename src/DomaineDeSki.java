import java.io.*;
import java.util.ArrayList;

public class DomaineDeSki {
	private ArrayList<Sommet> sommets;
	private Sommet debut;
	private Sommet arriver;
	private int difficulte;//difficulte : 0 -> moyen, 1 ->experimenté
	
	//Constructeur.
	public DomaineDeSki(int difficulte){
		this.sommets = new ArrayList<Sommet>();
		this.debut = null;
		this.arriver = null;
		this.setDifficulte(difficulte);
	}
	
	public int getNombredeSommets(){
		//Renvoie le nombre de sommet que contient le graphe.
		return this.sommets.size();
	}
	
	public int getDifficulte(){
		return this.difficulte;
	}
	
	public void setDifficulte(int setting){
		this.difficulte = setting;
	}
	
	public void setDifficulte(String setting){
		if(setting.equals("normal")){
			setDifficulte(0);
		}
		else if(setting.equals("experimente")){
			setDifficulte(1);
		}
		else if(setting.equals("exit")){
			setDifficulte(-1);
			System.exit(0);
		}
		else{
			System.out.println("Erreur : la difficulté entrée n'est pas correcte.");
			setDifficulte(-1);
		}
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
		ArrayList<Sommet> traiter = new ArrayList<Sommet>();
		String plusCourtChemin = "";
		//Affichage de test Affiche les sommets suivies de leurs pistes sortantes.
//		for(Sommet s : sommets){
//			System.out.print(s.getNom()+" ");
//			for(Chemin c : s.getSortants()){
//				System.out.print(c.getNom()+" ");
//			}
//			System.out.println();
//		}
			
		//Si le sommet de départ et le sommet d'arriver existe, on peut continuer.
		if(sommetExiste(depart) && sommetExiste(arriver)){
			//On initialise les sommets. Si le sommet est le début, on met sa distance à 0 et son pere à null.
			//On stocke aussi les sommets de debut et d'arriver
			//On leur dit aussi qu'ils n'ont pas encore été traiter avec setTraiterFalse()
			if(depart.equals(arriver)){
				return "Vous �tes d�j� � "+depart+".";
			}
			for(Sommet s : sommets){
				if(s.getNom().equals(depart)){
					s.setDistance(0);
					aTraiter.add(s);//On ajoute le sommet de départ dans la liste des sommets à traiter (aTraiter)
					s.setPere(null);
					this.debut = s;
				}
				else if(s.getNom().equals(arriver)){
					this.arriver = s;
				}
				else{
					s.setDistance(100000000);
				}
				s.setTraiterFalse();
			}
			
			while(!(_isTraitementTerminer(aTraiter))){//Tant que pas tout les sommets ont été traité on continue de tourner
				//TODO: Warning: Si pas tous les sommets du graphe sont accessible alors on tournera dans le vide car on ne pourra pas atteindre certains des sommets mais on vérifie quand même si ceux ci sont traités. Ne devrait pas poser de problèmes dans notre graphe. A gerer si le temps est disponible. 
				Sommet enTraitement = _plusPetitNonTraiter(aTraiter);
				//On choisit le sommet qui a la plus petite distance et qui est disponible pour le traitement, c'est-à-dire qu'on la déjà rencontrer.
				//Première exécution il n'y a que le sommet de départ, donc enTraitement est this.debut, le sommet de départ.
				if(enTraitement != null){ //Si on a pas fini des les traiter, c'est à dire que _plusPetitNonTraiter ne renvoie pas null.
					for(Chemin c : enTraitement.getSortants()){
						//On regarde toutes les arêtes partant du sommet enTraitement
						for(Sommet s : sommets){
//							System.out.println("En traitement : " + enTraitement.getNom() + " Sommet : "+ s.getNom());
							if(c.getArriver().equals(s.getNom()) && !(traiter.contains(s))){//On trouve le sommet d'arriver de l'arête et si elle n'est pas déjà marquer à traiter, on l'ajoute à aTraiter.
								aTraiter.add(s);
//								System.out.println("Nouveau � traiter : " +s.getNom());
							}
							if(c.getArriver().equals(s.getNom())){//On identifie le sommet d'arriver de l'arrête
								//Et si on peut l'atteindre avec une distance plus courte alors on marque sa nouvelle distance
								//comme étant la distance qu'on a mis pour arriver jusqu'ici (enTraitement.getDistance()) 
								//et on y ajoute le taille de l'arêtes qu'on doit parcourir pour y arriver.
								//Son nouveau pere se trouve être le sommet sur lequel on se trouve actuellement et duquel on peut l'atteindre en le moins de temps possible (enTraitement).
								if(s.getDistance()>(enTraitement.getDistance()+c.getTaille())){
									s.setDistance(enTraitement.getDistance()+c.getTaille());
									s.setPere(enTraitement);
									s.setCheminArrivantPere(c);
//									System.out.println("Nouvelle distance : " + s.getNom() +" "+s.getDistance());
								}
							}
						}
					}
					enTraitement.setTraiter();
					//On marque enTraitement comme quoi il a été traiter.
					aTraiter.remove(enTraitement);
					traiter.add(enTraitement);
					//Et on l'enlève de la liste des sommets qu'il reste à traiter.
				}
			}
			
			plusCourtChemin = _getStringFromArriver(this.arriver);
		}
		else{
			System.out.println("Erreur : Le sommet de départ ou d'arrivé spécifié n'existe pas.");
		}
		return plusCourtChemin;
	}
		
	private String _getStringFromArriver(Sommet arr) {
		String plusCourtChemin ="";
		//Remonte la chaine de sommet depuis l'arriver vers le debut.
		Sommet actuel = this.arriver.getPere();
		switch(actuel.getCheminArrivantPere().getType()){
		case "téléski":
			plusCourtChemin = "Prenez le téléski "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "télésiege":
			plusCourtChemin = "Prenez le télésiège "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "télécabine":
			plusCourtChemin = "Prenez la télécabine "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "téléphérique":
			plusCourtChemin = "Prenez le téléphérique "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "noir":
			plusCourtChemin = "Prenez la piste noir "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "bleu":
			plusCourtChemin = "Prenez la piste bleu "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "vert":
			plusCourtChemin = "Prenez la piste verte "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		case "rouge":
			plusCourtChemin = "Prenez la piste rouge "+this.arriver.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
			break;
		default:
			plusCourtChemin = actuel.getCheminArrivantPere().getNom()+" -> "+plusCourtChemin;
			break;
		}
		plusCourtChemin = "Vous êtes arrivé à "+this.arriver.getNom()+" en "+this.arriver.getDistance()+" minutes.\n";
		while(actuel.getDistance()!= 0){
			plusCourtChemin = "Vous êtes à "+ actuel.getNom()+"\n"+plusCourtChemin;
			switch(actuel.getCheminArrivantPere().getType()){
			case "téléski":
				plusCourtChemin = "Prenez le téléski "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "télésiege":
				plusCourtChemin = "Prenez le télésiege "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "télécabine":
				plusCourtChemin = "Prenez la télécabine "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "téléphérique":
				plusCourtChemin = "Prenez le téléphérique "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "noir":
				plusCourtChemin = "Prenez la piste noir "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "bleu":
				plusCourtChemin = "Prenez la piste bleu "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "vert":
				plusCourtChemin = "Prenez la piste verte "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			case "rouge":
				plusCourtChemin = "Prenez la piste rouge "+actuel.getCheminArrivantPere().getNom() +"\n"+plusCourtChemin;
				break;
			default:
				plusCourtChemin = actuel.getCheminArrivantPere().getNom()+" -> "+plusCourtChemin;
				break;
			}
			
			//On marque plusCourtChemin à l'envers puisque qu'on remonte dans le graphe depuis l'arriver jusqu'au début grâce au père.
			actuel = actuel.getPere();
		}
		plusCourtChemin = "Vous êtes à " +this.debut.getNom()+ "\n" + plusCourtChemin;

		return _turnTiretEspace(plusCourtChemin);
	}

	private String _turnTiretEspace(String in){
		String res = "";
		for(int i = 0; i<in.length(); i++){
			if(in.charAt(i) == '_'){
				res+= " ";
			}
			else{
				res+=in.charAt(i);
			}
		}
		return res;
	}
	private boolean _isTraitementTerminer(ArrayList<Sommet> aTraiter) {
		//Vérifie si les sommets de aTraiter sont traités.
		for(Sommet s : aTraiter){
			if(!(s.isTraiter())){
				return false; //Si on tombe sur un sommet qui n'est pas traités on renvoie false, sinon on renvoie true.
			}
		}
		return true;
	}

	private Sommet _plusPetitNonTraiter(ArrayList<Sommet> aTraiter){
		Sommet min = null; //Cherche et renvoie le sommet dans aTraiter ayant la plus petite distance et donc celui qu'il faudra traiter le prochain.
		if(aTraiter.size() == 0){
			return null;
		}
		for(Sommet s : aTraiter){
			if(!(s.isTraiter())){
				min = s;
			}
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
		String nom = ""; String depart = ""; String arriver = ""; String type = ""; String StrTaille = ""; int taille = 0;
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
					type += in.charAt(i);				
				}
				else{
					tres++;
				}
			}
			else if(tres == 2){
				if(in.charAt(i) != ' '){
					depart += in.charAt(i);
				}
				else{
					tres++;
				}
			}
			else if(tres == 3){
				if(in.charAt(i) != ' '){
					arriver += in.charAt(i);				
				}
				else{
					tres++;
				}
			}
			else if(tres == 4){
				if(in.charAt(i) != ' ' || in.charAt(i)!= '\t'){
					StrTaille += in.charAt(i);
				}
				else{
					tres++;
				}
			}
		}
		if(StrTaille != ""){
			taille = Integer.parseInt(StrTaille);
			linkCheminASommet(new Chemin(nom, type, depart, arriver, taille));
		}
		
		
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
				s.ajouterCheminArrivant(aLinker);
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
