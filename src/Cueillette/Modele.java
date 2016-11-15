package Cueillette;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;





public class Modele {

	public static final int VIDE=0,INTERET=1,AGENT=2;//Constantes 
	public static final String SIZE_DEFAUT="100";//Constante taille map par d�faut
	private final ArrayList<Vue>listVue;//Liste des vues du MVC
	private int[][] monde;//Stockage de la grille
	private int[][] memoire;//Stockage de chaque nouveau monde
	private final ArrayList<Agent>listAgent;//Liste des agents sur la grille
	private int nbPas;//Nombre de pas des agents
	private int mode;//Designe le mode de recherche (Levy/aleatoire)
	private boolean repartition;//Designe le mode de repartition des patchs (aleatoire/paquets)
	private final double Pinteret;//Probabilit� de chaque case d'�tre un point d'interet (0<=Pinteret<=1)
	private final double Pagent;//Probabilit� d'une case qui n'est pas un point d'interet d'etre un agent (0<=Pagent<=1)
	private final double Pdensite;//Probabilit� d'une case d'un spot d'avoir un point d'interet (0<=Pdensite<=1)
	private int ninterets;//Nombre de point d'interet voulu
	private int nagents;//Nombre d'agent voulu
	private boolean run;//Deplacement des agents
	private int nombreInteret;
	private int memNbInteret;//Nombre de point d'interets sur la carte
	private boolean timer;//Un timer a deja ete lance
	private boolean news;//accelere l'interface
	boolean switchAffichage;//modifier la vue Plateau

	public Modele(){
		listVue=new ArrayList<>();
		listAgent=new ArrayList<>();
		nbPas=0;
		Pinteret=0.0001;
		Pagent=0.0001;
		Pdensite=0.0001;
		run=false;
		mode=1;
		repartition=false;
		nagents=1;
		ninterets=1;
		nombreInteret=0;
		memNbInteret=0;
		timer=false;
		news=false;
		switchAffichage=false;
		changeSize(SIZE_DEFAUT);
	}

	public void ajouterVue(Vue v){
		listVue.add(v);
	}
	public void majVues(){
		listVue.forEach(Vue::mettreAJour);
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
	public void setNews(boolean b){
		news=b;
	}
	public boolean getNews(){
		return news;
	}
	public void setMode(int s) {
		mode=s;
	}

	public void setRepartition(boolean r){
		repartition=r;
	}
	public void start(){
		run=true;

		ActionListener task = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(run){
					step();
					majVues();
				}
			}
		};
		if(!timer){
			Timer time=new Timer(0,task);
			time.setRepeats(true);
			time.start();
			timer=true;
		}
	}
	public void stop(){
		run=false;
	}
	public void step(){
		if(existeInteret()){
			deplacementAgent();
			nbPas++;
		}else{
			run=false;
		}
	}

	private void deplacementAgent(){
		for(Agent a : listAgent){
			monde[a.getX()][a.getY()]=3;
			//a.returnCenter();
			a.changerDeplacement(mode);
			a.deplacement();
			if(monde[a.getX()][a.getY()]==1){
				nombreInteret-=1;
			}
			monde[a.getX()][a.getY()]=2;
			majVues();
		}
	}

	private boolean existeInteret(){
		return nombreInteret > 0;
	}

	public void raz(){
		listAgent.clear();
		nbPas=0;
		nombreInteret=0;
		run=false;
		news=true;
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				monde[i][j]=0;
			}
		}
	}

	public void newMap(){
		raz();
		Random rand=new Random();
		int n = 0 ;
		int m = 0 ;
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				monde[i][j]=0;
			}
		}
		while(n<nagents || m<ninterets){
			for(int i=0;i<getSizeX();i++){
				for(int j=0;j<getSizeY();j++){					
					if(!repartition && rand.nextFloat()<=Pinteret && monde[i][j]==0 && m<ninterets){
						monde[i][j]=1;
						m++;
						nombreInteret++;
					}
					else if(repartition && rand.nextFloat()<=Pdensite && monde[i][j]==0){
						monde[i][j]=4;
					}
					else if(rand.nextFloat()<=Pagent && monde[i][j]==0 && n<nagents){
						monde[i][j]=2;
						n++;
						listAgent.add(new Agent(i,j,monde));
					}
				}
			}

			if(repartition)
			{
				for(int i=0;i<getSizeX();i++){
					for(int j=0;j<getSizeY();j++){
						if(monde[i][j]==4){
							for(int k=0;k<10;k++){
								int xi=i+rand.nextInt(3);
								int yj=j+rand.nextInt(3);
								if(xi>=getSizeX()) xi-=getSizeX();
								if(yj>=getSizeX()) yj-=getSizeX();
								if(yj<0) yj+=getSizeX()-1;
								if(xi<0) xi+=getSizeX()-1;
								if(monde[xi][yj]==0 && m<ninterets){
									monde[xi][yj]=1;
									m++;
									nombreInteret++;
								}
							}
							monde[i][j]=0;
						}
					}
				}
			}
		}
		if(listAgent.isEmpty()){
			newMap();
		}
		sauvegarder();
		majVues();

	}

	public void changeSize(String string){
		int x=Integer.parseInt(string);
		if(x<=5 || x>100)
			throw new NumberFormatException();
		if(ninterets>x*5)
			ninterets=x*5;
		if(nagents>x)
			nagents=x;
		listAgent.clear();
		monde=new int[x][x];
		memoire=new int[x][x];

	}

	private void ajouterAgent(){
		listAgent.clear();
		Random rand=new Random();
		while(listAgent.size()!=nagents){
			for(int i=0;i<getSizeX();i++){
				for(int j=0;j<getSizeY();j++){
					if(monde[i][j]!=1 && monde[i][j]!=2){
						monde[i][j]=0;
					}
					if(rand.nextFloat()<=Pagent && monde[i][j]!=1 && listAgent.size()<nagents){
						monde[i][j]=2;
						listAgent.add(new Agent(i,j,monde));
					}
				}
			}
		}
		sauvegarder();
	}

	private void sauvegarder(){
		for(int i=0;i<getSizeX();i++){
			System.arraycopy(monde[i], 0, memoire[i], 0, getSizeY());
		}
		memNbInteret=nombreInteret;
	}

	public void relancer(){
		news=true;
		nbPas=0;
		nombreInteret=memNbInteret;
		run=false;
		news=true;
		listAgent.clear();
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				monde[i][j]=memoire[i][j];
				if(memoire[i][j]==2){
					listAgent.add(new Agent(i,j,memoire));
				}
			}
		}
		majVues();
	}

	public void ouvrir(String s){
		try{
			FileReader flot= new FileReader(s);
			@SuppressWarnings("resource")
			BufferedReader filtre=new BufferedReader(flot);

			String ligne=filtre.readLine();
			if(Integer.parseInt(ligne)>0){
				changeSize(ligne);
				int x=Integer.parseInt(ligne);
				int j=0;
				nombreInteret=0;
				ligne=filtre.readLine();
				while(ligne!=null){
					for(int i=0;i<x;i++){
						if(ligne.charAt(i)=='*'){
							monde[j][i]=1;
							nombreInteret++;
						}else{
							monde[j][i]=0;
						}
					}
					j++;
					if(j>=x){
						j=x;
					}
					ligne=filtre.readLine();
				}
				sauvegarder();
				ajouterAgent();
				this.majVues();
			}else{
				System.out.println("Fichier illisible");
			}
		}catch(IOException e){
			System.out.println("Erreur lors de l'ouverture");
		}
	}

	public int getInteret() {
		return nombreInteret;
	}

	public int getNbAgent() {
		return listAgent.size();
	}

	public void nAgents(String s){
		int x=Integer.parseInt(s);
		if(x<=0 || x>monde.length)
			throw new NumberFormatException();
		nagents=x ;
	}

	public void nPatchs(String s){
		int x=Integer.parseInt(s);
		if(x<=0 || x>monde.length*5)
			throw new NumberFormatException();
		ninterets=x ;
	}

	public boolean getRun() {
		return run;
	}

	public int getInteretPourcent() {
		// TODO Auto-generated method stub
		float x= (float) nombreInteret/ninterets;
		return (int) (x*100);
	}
}
