package Deplacement;

import java.util.Random;

/**
 * Created by Victor on 15/11/2016.
 */
public class DeplacementAlea extends Deplacement{
    private static int dAlea = 10 ;
    private int destX=coordX;
    private int destY=coordY;
    private int horsX=0;
    private int horsY=0;


    public DeplacementAlea(int[][] monde, int x, int y) {
        super(monde,x,y);
    }

    @Override
    public void mouvement() {
            if(coordX==destX && coordY==destY){
                Random rand=new Random();
                destX+= (int) Math.round((rand.nextGaussian())*dAlea);
                destY+=(int) Math.round((rand.nextGaussian())*dAlea);
                if(destX<0) horsX=destX + (monde.length-1);
                else if(destX>monde.length-1) horsX=destX - (monde.length-1);
                if(destY<0) horsY=destY + (monde.length-1);
                else if(destY>monde.length-1) horsY=destY - (monde.length-1);
                //System.out.println("destX: "+destX+" - destY: "+ destY);
            }
            if(horsX!=0){
                destX=horsX;
                horsX=0;
            }
            coordX=destX;

            if(horsY!=0){
                destY=horsY;
                horsY=0;
            }
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

    public static int getdAlea() {
        return dAlea;

    }
}
