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
		
	}
}
