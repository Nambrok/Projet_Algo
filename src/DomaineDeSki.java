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
		System.out.println(in);
	}

}
