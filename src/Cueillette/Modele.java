package Cueillette;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;





public class Modele {
	
	public static final int VIDE=0,INTERET=1,AGENT=2;//Constantes 
	protected ArrayList<Vue>listVue;//Liste des vues du MVC
	protected int[][] monde;//Stockage de la grille
	protected ArrayList<Agent>listAgent;//Liste des agents sur la grille
	protected int nbPas;//Nombre de pas des agents
	protected boolean mode;//Designe le mode de recherche (Levy/aleatoire)
	protected double Pinteret;//Probabilit� de chaque case d'�tre un point d'interet (0<=Pinteret<=1)
	protected double Pagent;//Probabilit� d'une case qui n'est pas un point d'interet d'etre un agent (0<=Pagent<=1)
	
	public Modele(int[][] tab){
		monde=tab;
		listVue=new ArrayList<>();
		listAgent=new ArrayList<>();
		nbPas=0;
		Pinteret=0.25;
		Pagent=0.01;
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
		listAgent.clear();
			for(int i=0;i<getSizeX();i++){
				for(int j=0;j<getSizeY();j++){
					if(rand.nextFloat()<=Pinteret){
						monde[i][j]=1;
					}else{
						if(rand.nextFloat()<=Pagent){
							monde[i][j]=2;
							listAgent.add(new Agent(i,j));
						}else{
							monde[i][j]=0;
						}
					}
				}
			}
		if(listAgent.isEmpty()){
			newMap();
		}
		majVues();
	}
	

	public void changeSize(String string, String string2) {
		int x=Integer.parseInt(string);
		int y=Integer.parseInt(string2);
		if(x<=0 || y<=0)
			throw new NumberFormatException();
		monde=new int[x][y];
		newMap();
		majVues();
	}
	
	public void ouvrir(String s){
		try{
			FileReader flot= new FileReader(s);
			@SuppressWarnings("resource")
			BufferedReader filtre=new BufferedReader(flot);
			
			String ligne=filtre.readLine();
			if(Integer.parseInt(ligne)>0){
				changeSize(ligne,ligne);
				while(ligne!=null){
					//TODO mettre les lignes dans le tableau
					ligne=filtre.readLine();
				}
				this.majVues();
			}else{
				System.out.println("Fichier illisible");
			}
		}catch(IOException e){
			System.out.println("Erreur lors de l'ouverture");
		}
	}
	
	
}
