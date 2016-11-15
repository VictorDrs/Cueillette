package Deplacement;

/**
 * Created by Victor on 15/11/2016.
 */
public abstract class Deplacement {
    protected final int[][] monde;
    protected int coordX,coordY;

    protected Deplacement(int[][] monde,int x,int y) {
        this.monde = monde;
        coordX=x;
        coordY=y;
    }

    public abstract void mouvement();


    protected void verifDim(){
        do{
            if(coordX>=monde.length){
                coordX=coordX-monde.length;
            }
            if(coordY>=monde.length){
                coordY=coordY-monde.length;
            }
            if(coordX<0){
                coordX=monde.length+coordX;
            }
            if(coordY<0){
                coordY=monde.length+coordY;
            }
        }while(coordY<0 || coordX<0 || coordY>=monde.length || coordX>=monde.length);
    }
    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
}
