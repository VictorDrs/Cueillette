package Vue;

import Cueillette.Modele;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Victor on 24/11/2016.
 */
public class VueControlStat extends JPanel implements Vue{
    protected Modele modele;
    protected JRadioButton levy;
    protected JRadioButton alea;
    protected JRadioButton perso;
    protected JRadioButton interetAlea;
    protected JRadioButton interetPaquet;
    protected JButton taille;
    protected JButton nbAgent;
    protected JButton nbInteret;
    protected JButton lancer;
    protected JButton reset;
    protected JButton partie;

    public VueControlStat(Modele mod) {
        super();
        modele=mod;
        ButtonGroup deplacement=new ButtonGroup();
        ButtonGroup repartition=new ButtonGroup();
        levy=new JRadioButton("Vol de levy");
        alea=new JRadioButton("Deplacement Aleatoire");
        perso=new JRadioButton("Deplacement perso");
        interetAlea=new JRadioButton("Repartition aleatoire");
        interetPaquet=new JRadioButton("Repartition par paquet");
        taille=new JButton("Changer la taille");
        nbAgent=new JButton("Nombre d'agent");
        nbInteret=new JButton("Nombre d'interet");
        lancer=new JButton("Lancer");
        reset=new JButton("Reset");
        partie=new JButton("Nombre de partie");




        partie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    modele.setNbPartieStat(JOptionPane.showInputDialog("Nombre de parties"));

                    mettreAJour();
                }catch(Exception NumberFormatException){
                    JOptionPane.showMessageDialog(null,"Entrez un nombre positif (100>taille>6)","Alerte",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        lancer.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 modele.majVuesStat();
             }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.razStat();
                modele.setResetStat(true);
                modele.majVuesStat();
            }
        });

        interetAlea.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.setRepartition(false);
            }
        });

        interetPaquet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.setRepartition(true);
            }
        });
        levy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                modele.setModeStat(2);
            }
        });
        alea.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.setModeStat(1);
            }
        });
        perso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.setModeStat(3);
            }
        });
        taille.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modele.changeSizeStat(JOptionPane.showInputDialog("Largeur de la grille"));
                    mettreAJour();
                }catch(Exception NumberFormatException){
                    JOptionPane.showMessageDialog(null,"Entrez un nombre positif (100>taille>6)","Alerte",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        nbAgent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modele.nAgentsStat(JOptionPane.showInputDialog("Nombre d'agents"));
                    mettreAJour();
                }catch(Exception NumberFormatException){
                    JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        nbInteret.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    modele.nPatchsStat(JOptionPane.showInputDialog("Nombre de patchs"));
                    mettreAJour();
                }catch(Exception NumberFormatException){
                    JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deplacement.add(levy);
        deplacement.add(alea);
        deplacement.add(perso);

        repartition.add(interetAlea);
        repartition.add(interetPaquet);
        this.add(levy);
        this.add(alea);
        this.add(perso);


        this.add(taille);
        this.add(nbAgent);
        this.add(nbInteret);
        this.add(partie);

        this.add(interetAlea);
        this.add(interetPaquet);

        this.add(lancer);
        this.add(reset);

        this.setLayout(new GridLayout(6,2,1,20));
        mettreAJour();
    }

    @Override
    public void mettreAJour() {
        taille.setText("Changer la taille : "+modele.getTailleStat());
        nbAgent.setText("Nombre d'agent : "+modele.getNbAgentStat());
        nbInteret.setText("Nombre d'interet : "+modele.getNbInteretStat());
        partie.setText("Nombre de partie : "+modele.getNbPartieStat());
    }
}
