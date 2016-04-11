import java.io.*;
import java.util.ArrayList;


public class DomaineDeSki {
	private ArrayList<Sommet> sommets;
	
	public DomaineDeSki(){
		this.sommets = new ArrayList<Sommet>();	
	}
	
	public int getNombredeSommets(){
		return this.sommets.size();
	}
	
	public String plusCourtChemin_Djikstra(String depart, String arriver){
		String plusCourtChemin = "";
		
		return plusCourtChemin;
	}
	
	
	public void chargerDonnees(String nomFile){
		try{
			BufferedReader read = new BufferedReader(new FileReader(nomFile));
			String in = read.readLine();
			while(in != null){
				_CreationPisteFromString(in);
				in = read.readLine();
			}
			read.close();
			
		}
		catch(IOException err){
			System.out.println("Exception : "+ err);
			System.out.println("Problème rencontrée lors de la lecture de la base de données.");
		}
	}
	
	private void _CreationPisteFromString(String in){
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
		sommetsExistant(new Chemin(nom, depart, arriver, taille));
//		this.pistes.add(new Chemin(nom, depart, arriver, taille));
	}
	
	public void sommetsExistant(Chemin aLinker){
		int existe = 0;
		for(Sommet s : sommets){
			if(s.getNom().equals(aLinker.getDepart())){
				s.ajouterAretesSortantes(aLinker);
				existe = 1;
			}
		}
		if(existe == 0){
			sommets.add(new Sommet(aLinker.getDepart(), aLinker));
		}
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i<this.getNombredeSommets(); i++){
			str+= this.sommets.get(i).toString()+"\n";
		}
		 
		return str;
	}
	
	public String afficherSommets(){
		String str = "";
		
		for(Sommet s : sommets){
			s.getNom();
		}
		
		return str;
	}
}
