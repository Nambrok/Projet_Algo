import java.io.*;
import java.util.ArrayList;


public class DomaineDeSki {
	private ArrayList<Chemin> pistes;
	
	public DomaineDeSki(){
		this.pistes = new ArrayList<Chemin>();	
	}
	
	public int getNombredePiste(){
		return this.pistes.size();
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
		this.pistes.add(new Chemin(nom, depart, arriver, taille));
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i<this.pistes.size(); i++){
			str+= this.pistes.get(i).toString()+"\n";
		}
		 
		return str;
	}
}
