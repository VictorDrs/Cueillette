package Deplacement;

import Cueillette.Monde;

/**
 * Created by Victor on 15/11/2016.
 */
public abstract class Deplacement {
    protected final Monde monde;
    protected int coordX,coordY;

    protected Deplacement(Monde monde,int x,int y) {
        this.monde = monde;
        coordX=x;
        coordY=y;
    }

    public abstract void mouvement();


    protected void verifDim(){
        do{
            if(coordX>=monde.getSizeX()){
                coordX=coordX-monde.getSizeX();
            }
            if(coordY>=monde.getSizeX()){
                coordY=coordY-monde.getSizeX();
            }
            if(coordX<0){
                coordX=monde.getSizeX()+coordX;
            }
            if(coordY<0){
                coordY=monde.getSizeX()+coordY;
            }
        }while(coordY<0 || coordX<0 || coordY>=monde.getSizeX() || coordX>=monde.getSizeX());
    }
    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

}
