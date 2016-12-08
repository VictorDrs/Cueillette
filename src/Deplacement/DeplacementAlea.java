package Deplacement;

import Cueillette.Monde;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Victor on 15/11/2016.
 */
public class DeplacementAlea extends Deplacement{
	private static final int DALEA = 10;
	private static int dAlea = DALEA ;
	private int destX=coordX;
	private int destY=coordY;


	public DeplacementAlea(Monde monde, int x, int y) {
		super(monde,x,y);
	}

	@Override
	public void mouvement() {
		if(coordX==destX && coordY==destY){

			do{
				double rand = ThreadLocalRandom.current().nextDouble(-1, 1);
				destX+= (int) (Math.round((rand*dAlea)%monde.getSizeX()));
				rand = ThreadLocalRandom.current().nextDouble(-1, 1);
				destY+=(int) (Math.round((rand*dAlea)%monde.getSizeX()));
				if(destX<0) destX=destX + (monde.getSizeX());
				else if(destX>monde.getSizeX()-1) destX=destX - (monde.getSizeX());
				if(destY<0) destY=destY + (monde.getSizeX());
				else if(destY>monde.getSizeX()-1) destY=destY - (monde.getSizeX());	
			}while(coordX==destX && coordY==destY);

		}
		coordX=destX;
		coordY=destY;

		/*Random rand=new Random();
		int bouge=rand.nextInt(4);
		if(bouge==0){
			x+=1;
		}else if(bouge==1){
			x-=1;
		}else if(bouge==2){
			y+=1;
		}else if(bouge==3){
			y-=1;
		}*/
		verifDim();
	}

	public static void setdAlea(int dAlea) {
		DeplacementAlea.dAlea = dAlea;
	}

	public static int getDAlea() {
		return dAlea;

	}

	public static int getDDefaut() {
		return DALEA;
	}
}
