package Cueillette;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Victor on 17/11/2016.
 */
public class Monde {
    protected int[][] monde;
    private boolean repartition;//Designe le mode de repartition des patchs (aleatoire/paquets)
    private final double Pinteret;//Probabilit? de chaque case d'?tre un point d'interet (0<=Pinteret<=1)
    private final double Pagent;//Probabilit? d'une case qui n'est pas un point d'interet d'etre un agent (0<=Pagent<=1)
    private final double Pdensite;//Probabilit? d'une case d'un spot d'avoir un point d'interet (0<=Pdensite<=1)
    protected final static int DISTANCE = 10 ;//Valeur par défaut du diamètre
    protected final static int DENSITE = 10 ;//Valeur par défaut de la densité
    protected static int distance = DISTANCE ;//Diametre dans lequel apparaissent les points d'interets repartis par paquets
    protected static int densite = DENSITE ;//Densite des points d'interets repartis par paquets au sein d'un paquet
    private int ninterets;//Nombre de point d'interet voulu
    private int nagents;//Nombre d'agent voulu
    private int nombreInteret;

    public Monde(){
        monde=new int[100][100];
        Pinteret=0.0001;
        Pagent=0.0001;
        Pdensite=0.0001;
        repartition=false;
        nagents=10;
        ninterets=10;
        nombreInteret=0;
    }

    public Monde(int x, int y){
        monde=new int[x][y];
        Pinteret=0.0001;
        Pagent=0.0001;
        Pdensite=0.0001;
        repartition=false;
        //if(ninterets>x*5)
            ninterets=x*5;
        //if(nagents>x)
            nagents=x;
        nombreInteret=0;
    }

    public void raz() {
        nombreInteret=0;
        for(int i=0;i<monde.length;i++){
            for(int j=0;j<monde[0].length;j++){
                monde[i][j]=0;
            }
        }
    }

    public ArrayList<Agent> newMap(){
        ArrayList<Agent>listAgent=new ArrayList<>();
        Random rand=new Random();
        int n = 0 ;
        int m = 0 ;
        for(int i=0;i<getSizeX();i++){
            for(int j=0;j<getSizeY();j++){
                monde[i][j]=0;
            }
        }
        while(n<nagents || m<ninterets){
            for(int i=0;i<getSizeX();i++){
                for(int j=0;j<getSizeY();j++){
                    if(!repartition && rand.nextFloat()<=Pinteret && monde[i][j]==0 && m<ninterets){
                        monde[i][j]=1;
                        m++;
                        nombreInteret++;
                    }
                    else if(repartition && rand.nextFloat()<=Pdensite && monde[i][j]==0){
                        monde[i][j]=4;
                    }
                    else if(rand.nextFloat()<=Pagent && monde[i][j]==0 && n<nagents){
                        monde[i][j]=2;
                        n++;
                        listAgent.add(new Agent(i,j,this));
                    }
                }
            }

            if(repartition)
            {
                for(int i=0;i<getSizeX();i++){
                    for(int j=0;j<getSizeY();j++){
                        if(monde[i][j]==4){
                            for(int k=0;k<densite;k++){
                                int xi=i+rand.nextInt(getSizeX()/distance);
                                int yj=j+rand.nextInt(getSizeX()/distance);
                                if(xi>=getSizeX()) xi-=getSizeX();
                                if(yj>=getSizeX()) yj-=getSizeX();
                                if(yj<0) yj+=getSizeX()-1;
                                if(xi<0) xi+=getSizeX()-1;
                                if(monde[xi][yj]==0 && m<ninterets){
                                    monde[xi][yj]=1;
                                    m++;
                                    nombreInteret++;
                                }
                            }
                            monde[i][j]=0;
                        }
                    }
                }
            }
        }
        return listAgent;
    }

    public void cmpInteret() {
        nombreInteret=0;
        for (int i = 0; i < monde.length; i++) {
            for (int j = 0; j < monde[0].length; j++) {
                if(monde[i][j]==1){
                    nombreInteret++;
                }
            }
        }
    }

    public ArrayList<Agent> ajouterAgent() {
        ArrayList<Agent>listAgent=new ArrayList<>();
        Random rand=new Random();
        while(listAgent.size()!=nagents){
            for(int i=0;i<getSizeX();i++){
                for(int j=0;j<getSizeY();j++){
                    if(monde[i][j]!=1 && monde[i][j]!=2){
                        monde[i][j]=0;
                    }
                    if(rand.nextFloat()<=Pagent && monde[i][j]!=1 && listAgent.size()<nagents){
                        monde[i][j]=2;
                        listAgent.add(new Agent(i,j,this));
                    }
                }
            }
        }
        return listAgent;
    }

    public void setRepartition(boolean repartition) {
        this.repartition = repartition;
    }

    public boolean existeInteret() {
        return nombreInteret>0;
    }

    public void checkInteret(int x, int y) {
        if(monde[x][y]==1){
            nombreInteret-=1;
        }
    }

    public void setNinterets(int ninterets) {
        this.ninterets = ninterets;
    }
    public void setNagents(int x){
        nagents=x;
    }
    public int getSizeX(){
        return monde.length;
    }
    public int getSizeY(){
        return monde[0].length;
    }
    public int getCase(int x,int y){
        return monde[x][y];
    }
    public void setCase(int x, int y,int val){monde[x][y]=val;}

    public int getNinterets() {
        return ninterets;
    }

    public int getNombreInteret() {
        return nombreInteret;
    }

    public void setNombreInteret(int nombreInteret) {
        this.nombreInteret = nombreInteret;
    }

	public static int getDistance() {
		return distance;
	}
	
	public static int getDensite() {
		return densite;
	}
	
	public static int getDistanceDefaut() {
		return DISTANCE;
	}
	
	public static int getDensiteDefaut() {
		return DENSITE;
	}

	public static void setDistance(int d) {
		distance = d;
	}
	
	public static void setDensite(int d) {
		densite = d;
	}

}
