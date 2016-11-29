package Cueillette;

/**
 * Created by Victor on 24/11/2016.
 */
public class Statistiques{
    protected Monde[] monde;
    protected int[] nbPas;

    public Statistiques(){
        monde=new Monde[0];
        nbPas=new int[0];
    }

    public String[] run(int n,int mode){
        String[] s = new String[n];
        monde=new Monde[n];
        nbPas=new int[n];
        for(int i=0;i<n;i++){
            monde[i]=new Monde();
            monde[i].newMap();
            while (monde[i].isRunning()){
                monde[i].deplacementAgent(mode);
            }
            nbPas[i]=monde[i].getNbPas();
            s[i]=""+nbPas[i];
        }
        return s;
    }
}
