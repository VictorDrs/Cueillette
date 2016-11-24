package Cueillette;

/**
 * Created by Victor on 24/11/2016.
 */
public class Statistiques{
    protected Modele[] modele;
    protected int[] nbPas;



    public Statistiques(){
        modele=new Modele[0];
        nbPas=new int[0];
    }

    public String[] run(int n){
        String[] s = new String[n];
        modele=new Modele[n];
        nbPas=new int[n];
        for(int i=0;i<n;i++){
            modele[i]=new Modele();
            modele[i].newMap();
            while (modele[i].isRunning()){
                modele[i].step();
            }
            nbPas[i]=modele[i].getNbPas();
            s[i]=""+nbPas[i];
        }
        return s;
    }
    public void affiche(){
        int j=0;
        for(int i : nbPas){
            j+=i;
        }
        System.out.println("   "+j/nbPas.length);
    }
}
