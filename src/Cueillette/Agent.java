package Cueillette;

import Deplacement.*;

public class Agent {
	private int x;
	private int y;//Coord de l'agent
	private final Monde monde;//Utile pour le deplacement (dimension du monde)
	private Deplacement dep;
	private int memDep;
	private int memX;
	private int memY;


	public Agent(int x,int y,Monde tab){
		this.x=x;
		this.y=y;
		memX=x;
		memY=y;
		monde=tab;
		memDep=3;
		dep=new DeplacementPerso(monde,x,y);
	}


	public void changerDeplacement(int d){
		if(memDep!=d) {
			memDep = d;
			switch (memDep) {
				case 1:
					dep = new DeplacementAlea(monde, x, y);
					break;
				case 2:
					dep = new DeplacementLevy(monde, x, y);
					break;
				case 3:
					dep = new DeplacementPerso(monde, x, y);
					break;
				default:
					dep = new DeplacementAlea(monde, x, y);
			}
		}
	}

	public void returnCenter(){
		x=50;
		y=50;
	}


	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public int getMemX() {
		return memX;
	}

	public int getMemY() {
		return memY;
	}

	public void deplacement() {
		dep.mouvement();
		memX=x;
		memY=y;
		x=dep.getCoordX();
		y=dep.getCoordY();
	}
}
