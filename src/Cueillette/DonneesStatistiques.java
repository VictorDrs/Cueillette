package Cueillette;

import Deplacement.DeplacementAlea;
import Deplacement.DeplacementLevy;

public class DonneesStatistiques {
	
	private int[] nbPas;
	private int taille;
	private int nbAgent;
	private int nbInteret;
	private int mode;
	private int nbPartie;
	private double alphaLevy;
	private int nLevy;
	private int dAlea;
	private int distPaquets;
	private int densPaquets;
	
	public DonneesStatistiques(int[] np, int t, int na, int ni, int m, int nb) {
		nbPas = np;
		taille = t;
		nbAgent = na;
	    nbInteret = ni;
	    mode = m;
	    nbPartie =nb;
	    alphaLevy = DeplacementLevy.getAlpha();
	    nLevy = DeplacementLevy.getN();
	    dAlea = DeplacementAlea.getDAlea();
	    distPaquets = Monde.getDistance();
	    densPaquets = Monde.getDensite();
	}
	
	public int getMode() {
		return mode;
	}
	
	public int getNbAgent() {
		return nbAgent;
	}
	
	public int getNbInteret() {
		return nbInteret;
	}
	
	public int getNbPartie() {
		return nbPartie;
	}
	
	public int[] getNbPas() {
		return nbPas;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public double getAlphaLevy() {
		return alphaLevy;
	}
	
	public int getdAlea() {
		return dAlea;
	}
	
	public int getDensPaquets() {
		return densPaquets;
	}
	
	public int getDistPaquets() {
		return distPaquets;
	}
	
	public int getnLevy() {
		return nLevy;
	}
	
}
