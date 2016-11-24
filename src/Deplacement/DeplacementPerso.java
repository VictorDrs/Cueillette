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
        int nbPas=0;
        Random rand = new Random();
        while (dejaVu[coordX][coordY] &&  nbPas<350) {
            coordY+=rand.nextInt(monde.getSizeX());
            coordX+=rand.nextInt(monde.getSizeX());
            verifDim();
            nbPas++;
        }
        dejaVu[coordX][coordY]=true;
        verifDim();
    }

}
