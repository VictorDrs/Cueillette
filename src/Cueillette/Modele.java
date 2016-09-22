package Cueillette;
import java.util.ArrayList;
import java.util.Random;



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
	
	public void newMap(){
		Random rand=new Random();
			for(int i=0;i<getSizeX();i++){
				for(int j=0;j<getSizeY();j++){
					if(rand.nextFloat()>0.5){
						monde[i][j]=1;
					}else{
						monde[i][j]=0;
					}
				}
			}
		majVues();
	}
	

	public void changeSize(String string, String string2) {
		int x=Integer.parseInt(string);
		int y=Integer.parseInt(string2);
		if(x<=0 || y<=0)
			throw new NumberFormatException();
		Random rand=new Random();
		monde=new int[x][y];
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				if(rand.nextFloat()>0.5){
					monde[i][j]=1;
				}else{
					monde[i][j]=0;
				}
			}
		}
		majVues();
		
	}
	
}
