package Cueillette;
import java.util.ArrayList;



public class Modele {
	
	public static final int VIDE=0,INTERET=1,AGENT=2;
	protected ArrayList<Vue>listVue;
	protected ArrayList<String>listCase;
	protected int[][] monde;
	protected ArrayList<Agent>listAgent;
	protected int nbPas;
	protected boolean mode;
	public Modele(int[][] tab){
		monde=tab;
		listCase=new ArrayList<>();
		listVue=new ArrayList<>();
		listAgent=new ArrayList<>();
		nbPas=0;
		
	}
	
	public void ajouterVue(Vue v){
		listVue.add(v);
	}
	public void majVues(){
		for(Vue v : listVue){
			v.mettreAJour();
		}
	}
	public int getSizeX(){
		return monde.length;
	}
	public int getSizeY(){
		return monde[0].length;
	}
	public int getCase(int x,int y){
		return monde[x][y];
	}
	public int getNbPas(){
		return nbPas;
	}
	
	public void setMode(boolean s) {
		mode=s;
	}
	
	
}
