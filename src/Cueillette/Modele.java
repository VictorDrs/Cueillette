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
	protected double Pinteret;//Probabilité de chaque case d'être un point d'interet (0<=Pinteret<=1)
	protected double Pagent;//Probabilité d'une case qui n'est pas un point d'interet d'etre un agent (0<=Pagent<=1)
	protected double Pdensite;//Probabilité d'une case d'un spot d'avoir un point d'interet (0<=Pdensite<=1)
	protected boolean run;
	protected int nombreInteret,memNbInteret;//Nombre de point d'interets
	
	public Modele(){
		listVue=new ArrayList<>();
		listAgent=new ArrayList<>();
		nbPas=0;
		Pinteret=0.03;
		Pagent=0.01;
		Pdensite=0.01;
		run=false;
		mode=true;
		nombreInteret=0;
		memNbInteret=0;
		changeSize("30");
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
			Timer time=new Timer(100,task);
			time.setRepeats(true);
			time.start();
	}
	public void stop(){
		run=false;
	}
	public void step(){
		if(existeInteret()){
			deplacementAgent();
			nbPas++;
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
		//System.out.println("nombre interet : "+nombreInteret);
		if(nombreInteret>0)
			return true;
		return false;
	}
	public void newMap(){
		Random rand=new Random();
		listAgent.clear();
		nbPas=0;
		nombreInteret=0;
		run=false;
			for(int i=0;i<getSizeX();i++){
				for(int j=0;j<getSizeY();j++){					
					if(!repartition && rand.nextFloat()<=Pinteret){
						monde[i][j]=1;
						memoire[i][j]=1;
						nombreInteret++;
					}
					else if(repartition && rand.nextFloat()<=Pdensite){
						monde[i][j]=3;
					}
					else if(rand.nextFloat()<=Pagent){
							monde[i][j]=2;
							memoire[i][j]=2;

							listAgent.add(new Agent(i,j,monde));
					}
					else{ 
						monde[i][j]=0;
						memoire[i][j]=0;
					}

				}
			}
			if(repartition)
			{
				for(int i=0;i<getSizeX();i++){
					for(int j=0;j<getSizeY();j++){
						if(monde[i][j]==3){
							for(int k=0;k<10;k++){
								int xi=i+rand.nextInt(3);
								int yj=j+rand.nextInt(3);
								if(xi>=getSizeX()) xi-=getSizeX();
								if(yj>=getSizeX()) yj-=getSizeX();
								monde[xi][yj]=1;
								memoire[xi][yj]=1;
								nombreInteret++;
							}
							monde[i][j]=0;
							memoire[i][j]=0;
						}
					}
				}
			}
			memNbInteret=nombreInteret;
		if(listAgent.isEmpty()){
			newMap();
		}
		majVues();
	}
	public void changeSize(String string){
		int x=Integer.parseInt(string);
		if(x<=0)
			throw new NumberFormatException();
		monde=new int[x][x];
		memoire=new int[x][x];
		newMap();
		majVues();
	}
	public void ajouterAgent(){
		listAgent.clear();
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				Random rand=new Random();
				if(rand.nextFloat()<=Pagent && monde[i][j]!=1){
					monde[i][j]=2;
					listAgent.add(new Agent(i,j,monde));
				}
			}
		}
		if(listAgent.isEmpty()){
			ajouterAgent();
		}
	}
	public void relancer(){
		nbPas=0;
		nombreInteret=memNbInteret;
		for(int i=0;i<getSizeX();i++){
			for(int j=0;j<getSizeY();j++){
				monde[i][j]=memoire[i][j];
			}
		}
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
				ligne=filtre.readLine();
				while(ligne!=null){
					for(int i=0;i<x;i++){
						if(ligne.charAt(i)=='*'){
							monde[j][i]=1;
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

	
	
}
