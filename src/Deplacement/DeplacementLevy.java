package Deplacement;

import Cueillette.Monde;

import java.util.Random;

/**
 * Created by Victor on 15/11/2016.
 */
public class DeplacementLevy extends Deplacement{
    public static double alpha = 2;
    static int n = 100;

    public DeplacementLevy(Monde monde, int x, int y) {
        super(monde,x,y);
    }


    private void deplacement(double dist){
        dist = (Math.abs(dist) % monde.getSizeX());
        if ((int) dist == 0) {
            dist++;
        }
        double alph=angle();
        this.coordX+=Math.round(dist*Math.cos(alph));
        this.coordY+=Math.round(dist*Math.sin(alph));
        verifDim();
    }


    private double angle() {
        Random rand= new Random();
        return rand.nextDouble()*6.283185307179586;
    }


    @Override
    public void mouvement() {
        Random rand = new Random();
        double somme = 0;

        for (int i = 1; i <= n; i++) {
            somme += (rand.nextGaussian() / Math.pow(Math.abs(rand.nextGaussian()), 1 / alpha));
        }

        somme = (somme / Math.pow(n, 1 / alpha));
        deplacement(somme);

/*
		destX+= (int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),2))%monde.length);
		destY+=(int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),2))%monde.length);

		if(destX<0) destX+=(monde.length-1);
		else if(destX>monde.length-1) destX-=(monde.length-1);

		if(destY<0) destY+=(monde.length-1);
		else if(destY>monde.length-1) destY-=(monde.length-1);

		x=destX;
		y=destY;

		System.out.println("x: "+x+" - y: "+y);

		verifDim();
*/
		/*
		if(x==destX && y==destY){
			Random rand=new Random();
			destX+= (int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),1.5)) % monde.length);
			destY+=(int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),1.5)) % monde.length);
			if(destX<0) horsX=destX + (monde.length-1);
			else if(destX>monde.length-1) horsX=destX - (monde.length-1);
			if(destY<0) horsY=destY + (monde.length-1);
			else if(destY>monde.length-1) horsY=destY - (monde.length-1);
			System.out.println("destX: "+destX+" - destY: "+ destY);
		}
		if(horsX!=0){
			destX=horsX;
			horsX=0;
		}
		x=destX;

		if(horsY!=0){
			destY=horsY;
			horsY=0;
		}
		y=destY;


		if(horsX!=0 && x-1<0){
			deplacement(1);
			destX=horsX;
			horsX=0;
		}else if(horsX!=0 && monde.length-1<x+1){
			deplacement(0);
			destX=horsX;
			horsX=0;
		}else if(horsY!=0 && y-1<0){
			deplacement(3);
			destY=horsY;
			horsY=0;
		}else if(horsY!=0 && monde.length-1<y+1){
			deplacement(2);
			destY=horsY;
			horsY=0;
		}

		else if(x>destX){
			deplacement(1);
		}else if(x<destX){
			deplacement(0);
		}else if(y>destY){
			deplacement(3);
		}else if(y<destY){
			deplacement(2);
		}
		verifDim();
    */}

    public static double getAlpha() {
        return alpha;
    }

    public static int getN() {
        return n;
    }

    public static void setAlpha(double alpha) {
        DeplacementLevy.alpha = alpha;
    }

    public static void setN(int n) {
        DeplacementLevy.n = n;
    }
}

