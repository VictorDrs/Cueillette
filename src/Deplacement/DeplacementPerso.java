package Deplacement;

import Cueillette.Monde;

import java.util.Random;

/**
 * Created by Victor on 15/11/2016.
 */
public class DeplacementPerso extends Deplacement{
    private static boolean[][] dejaVu;

    public DeplacementPerso(Monde monde, int x, int y) {
        super(monde,x,y);
        dejaVu=new boolean[monde.getSizeX()][monde.getSizeY()];
    }

    @Override
    public void mouvement() {
        Random rand = new Random();
        coordY+=1;
        verifDim();
        while (dejaVu[coordX][coordY]) {
            coordY-=1;
            coordX+=rand.nextInt(3)-1;
            verifDim();
        }
        dejaVu[coordX][coordY]=true;
        verifDim();
    }

}
