package Cueillette;
import java.util.ArrayList;



public class Modele {
	protected ArrayList<Vue>listVue;
	protected int[][] monde;
	protected ArrayList<Agent>listAgent;
	protected int nbPas;
	protected String mode;
	public Modele(int[][] tab){
		monde=tab;
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

	public void setMode(String s) {
		mode=s;
	}
	
}
