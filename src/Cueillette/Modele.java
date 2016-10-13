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
	protected ArrayList<Vue>listVue;//Liste des vues du MVC
	protected int[][] monde;//Stockage de la grille
	protected int[][] memoire;//Stockage de chaque nouveau monde
	protected ArrayList<Agent>listAgent;//Liste des agents sur la grille
	protected int nbPas;//Nombre de pas des agents
	protected boolean mode;//Designe le mode de recherche (Levy/aleatoire)
	protected boolean repartition;//Designe le mode de repartition des patchs (aleatoire/paquets)
	protected double Pinteret;//Probabilit� de chaque case d'�tre un point d'interet (0<=Pinteret<=1)
	protected double Pagent;//Probabilit� d'une case qui n'est pas un point d'interet d'etre un agent (0<=Pagent<=1)
	protected double Pdensite;//Probabilit� d'une case d'un spot d'avoir un point d'interet (0<=Pdensite<=1)
	protected int ninterets;//Nombre de point d'interet voulu
	protected int nagents;//Nombre d'agent voulu
	protected boolean run;//Deplacement des agents
	protected int nombreInteret,memNbInteret;//Nombre de point d'interets sur la carte
	protected boolean timer;//Un timer a deja ete lance
	protected boolean news;//accelere l'interface

	public Modele(){
		listVue=new ArrayList<>();
		listAgent=new ArrayList<>();
		nbPas=0;
		Pinteret=0.0001;
		Pagent=0.0001;
		Pdensite=0.0001;
		run=false;
		mode=true;
		repartition=false;
		nagents=1;
		ninterets=500;
		nombreInteret=0;
		memNbInteret=0;
		timer=false;
		news=false;
		changeSize("100");
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
	public void setNews(boolean b){
		news=b;
	}
	public boolean getNews(){
		return news;
	}
	public void setMode(boolean s) {
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

	public void deplacementAgent(){
		for(Agent a : listAgent){
			monde[a.getX()][a.getY()]=3;
			if(mode){
				a.deplacementLevy();
			}else{
				a.deplacementAlea();
			}
			if(monde[a.getX()][a.getY()]==1){
				nombreInteret-=1;
			}
			monde[a.getX()][a.getY()]=2;
		}
	}

	public boolean existeInteret(){
		if(nombreInteret>0)
			return true;
		return false;
	}
	public void newMap(){
		Random rand=new Random();
		listAgent.clear();
		nbPas=0;
		int n = 0 ;
		int m = 0 ;
		nombreInteret=0;
		run=false;
		news=true;
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

	public void ajouterAgent(){
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

	public void sauvegarder(){
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				memoire[i][j]=monde[i][j];
			}
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
