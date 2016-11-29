package Vue;

import Cueillette.Modele;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Victor on 24/11/2016.
 */
public class ChangeAffichage extends JPanel implements Vue{
    protected VueStatistiques vueStat;
    protected VueControlStat vueControlStat;
    protected VuePlateau vp;
    protected VueControl vc;
    protected VueScore vs;
    private boolean affichage;
    protected Modele modele;

    public ChangeAffichage(Modele mod){
        super();
        modele=mod;
        vueControlStat=new VueControlStat(mod);
        vp=new VuePlateau(mod);
        vc=new VueControl(mod);
        vs=new VueScore(mod);
        vueStat=new VueStatistiques(mod);
        modele.ajouterVue(vueStat);
        modele.ajouterVue(vp);
        modele.ajouterVue(vc);
        modele.ajouterVue(vs);
        modele.ajouterVue(vueControlStat);
        affichage=false;
        changeAffichage();
    }

    public void changeAffichage(){
        affichage = !affichage ;
        if(affichage){
            removeAll();
            setLayout(new BorderLayout());
            add(vp,BorderLayout.CENTER);
            add(vc,BorderLayout.EAST);
            add(vs,BorderLayout.SOUTH);
            modele.setNews(true);
            modele.majVues();
            repaint();
            validate();
            System.out.println("plateau");
        }else{
            removeAll();
            setLayout(new BorderLayout());
            add(vueStat, BorderLayout.CENTER);
            add(vueControlStat,BorderLayout.EAST);
            modele.raz();
            repaint();
            validate();
            System.out.println("vueStatistiques");
        }
        System.out.println("affichage: "+affichage);
    }
    public void mettreAJour() {
        if (modele.getSwitchAffichage()) {
            modele.setSwitchAffichage(affichage);
            changeAffichage();
        }
    }
}
