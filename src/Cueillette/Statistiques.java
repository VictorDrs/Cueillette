package Cueillette;

/**
 * Created by Victor on 24/11/2016.
 */
public class Statistiques{
    protected Monde[] monde;
    protected int[] nbPas;
    protected int taille;
    protected int nbAgent;
    protected int nbInteret;
    protected int mode;
    protected int nbPartie;
    protected boolean reset;

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean getReset() {
        return reset;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setNbAgent(int nbAgent) {
        if (nbAgent>taille)
            this.nbAgent=taille;
        this.nbAgent = nbAgent;
    }

    public void setNbInteret(int nbInteret) {
        if(nbInteret>5*taille)
            this.nbInteret=5*taille;
        this.nbInteret = nbInteret;
    }

    public int getSize() {
        return taille;
    }

    public int getNbAgent() {
        return nbAgent;
    }

    public int getNbInteret() {
        return nbInteret;
    }
    public void raz(){
        nbPas=null;
    }
    public int getNbPartie(){
        return nbPartie;
    }

    public void setNbPartie(int nbPartie) {
        this.nbPartie = nbPartie;
    }

    public Statistiques(){
        monde=new Monde[0];
        nbPas=new int[0];
        mode=3;
        taille=10;
        nbAgent=10;
        nbInteret=10;
        reset=false;
    }

    public String[] run(int n){
        nbPartie=n;
        String[] s = new String[n];
        monde=new Monde[n];
        nbPas=new int[n];
        if(nbInteret>5*taille)
            this.nbInteret=5*taille;
        if (nbAgent>taille)
            this.nbAgent=taille;
        //System.out.println("Mode : "+mode+" Taille : "+taille+" nb Agent : "+nbAgent+" nb Interet : "+nbInteret);
        for(int i=0;i<n;i++){
            monde[i]=new Monde(taille,taille);
            monde[i].setNagents(nbAgent);
            monde[i].setNinterets(nbInteret);
            monde[i].newMap();
            while (monde[i].isRunning()){
                monde[i].deplacementAgent(mode);
            }
            nbPas[i]=monde[i].getNbPas();
            s[i]=""+nbPas[i];
            System.out.println(s[i]);
        }
        return s;
    }

    public int getMoyenne(){
        int res=0;
        for(int i : nbPas){
            res=res+i;
        }
        return (res/nbPas.length);
    }

}
