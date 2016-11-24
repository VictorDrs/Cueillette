package Cueillette;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;





public class Modele {

	public static final int VIDE=0,INTERET=1,AGENT=2;//Constantes 
	public static final String SIZE_DEFAUT="100";//Constante taille map par d�faut
	private final ArrayList<Vue>listVue;//Liste des vues du MVC
	private Monde monde;//Stockage de la grille
	private Monde memoire;//Stockage de chaque nouveau monde
	private int mode;//Designe le mode de recherche (Levy/aleatoire)
	private boolean run;//Deplacement des agents
	private boolean timer;//Un timer a deja ete lance
	private boolean news;//accelere l'interface
	boolean switchAffichage;//modifier la vue Plateau
	private ArrayList<Agent> listAgent;

	public Modele(){
		listVue=new ArrayList<>();
		listAgent=new ArrayList<>();
		monde=new Monde();
		memoire=new Monde();
		monde.setNbPas(0);
		run=false;
		mode=1;
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
	public int getNbPas(){
		return monde.getNbPas();
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
	public int getSizeX(){
		return monde.getSizeX();
	}
	public int getSizeY(){
		return monde.getSizeY();
	}
	public int getCase(int x, int y){
		return monde.getCase(x,y);
	}

	public void setRepartition(boolean r){
		monde.setRepartition(r);
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
			monde.nbPasPlusUn();
		}else{
			run=false;
		}
	}

	private void deplacementAgent(){
		for(Agent a : listAgent){
			monde.setCase(a.getX(),a.getY(),3);
			a.changerDeplacement(mode);
			a.deplacement();
			monde.checkInteret(a.getX(),a.getY());
			monde.setCase(a.getX(),a.getY(),2);
		}
	}

	private boolean existeInteret(){
		return monde.existeInteret();
	}

	public void raz(){
		listAgent.clear();
		monde.setNbPas(0);
		run=false;
		monde.raz();
	}

	public void newMap(){
		raz();
		news=true;
		listAgent=monde.newMap();
		sauvegarder();
		majVues();
	}

	public void changeSize(String string){
		int x=Integer.parseInt(string);
		if(x<=5 || x>100)
			throw new NumberFormatException();
		listAgent.clear();
		monde=new Monde(x,x);
		memoire=new  Monde(x,x);
		newMap();
	}

	private void ajouterAgent(){
		listAgent.clear();
		listAgent=monde.ajouterAgent();
		sauvegarder();
	}

	private void sauvegarder(){
		for(int i=0;i<monde.getSizeX();i++){
			for (int j = 0; j <monde.getSizeY() ; j++) {
				memoire.setCase(i,j,monde.getCase(i,j));
			}
		}
		memoire.setNinterets(monde.getNinterets());
	}

	public void relancer(){
		news=true;
		monde.setNbPas(0);
		monde.setNinterets(memoire.getNinterets());
		run=false;
		news=true;
		listAgent.clear();
		for(int i=0;i<monde.getSizeX();i++){
			for(int j=0;j<monde.getSizeY();j++){
				monde.setCase(i,j,memoire.getCase(i,j));
				if(memoire.getCase(i,j)==2){
					listAgent.add(new Agent(i,j,memoire));
				}
			}
		}
		monde.cmpInteret();
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
				ligne=filtre.readLine();
				while(ligne!=null){
					for(int i=0;i<x;i++){
						if(ligne.charAt(i)=='*'){
							monde.setCase(j,i,1);
						}else{
							monde.setCase(j,i,0);
						}
					}
					j++;
					if(j>=x){
						j=x;
					}
					ligne=filtre.readLine();
				}

				ajouterAgent();
				monde.cmpInteret();
				sauvegarder();
				this.majVues();
			}else{
				System.out.println("Fichier illisible");
			}
		}catch(IOException e){
			System.out.println("Erreur lors de l'ouverture");
		}
	}

	public int getInteret() {
		return monde.getNombreInteret();
	}

	public int getNbAgent() {
		return listAgent.size();
	}

	public void nAgents(String s){
		int x=Integer.parseInt(s);
		if(x<=0 || x>monde.getSizeX())
			throw new NumberFormatException();
		monde.setNagents(x);
	}

	public void nPatchs(String s){
		int x=Integer.parseInt(s);
		if(x<=0 || x>monde.getSizeY()*5)
			throw new NumberFormatException();
		monde.setNinterets(x);
	}

	public boolean getRun() {
		return run;
	}

	public int getInteretPourcent() {
		float x= (float) monde.getNombreInteret()/monde.getNinterets();
		return (int) (x*100);
	}

	public ArrayList<Agent> getAgent() {
		return listAgent;
	}

	public boolean isRunning() {
		return monde.existeInteret();
	}
}
