package Vue;

import Cueillette.Modele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class VueStatistiques extends JPanel implements Vue{

	private JScrollPane lancerPartie;
	protected JTextArea affiche;
	private Modele modele;


	public VueStatistiques(Modele mod){
		super();
		this.initComponent();
		modele=mod;
	}
	
	public void initComponent(){
		affiche = new JTextArea("");
		affiche.setBounds(15,60,800,200);
		affiche.setEditable(false);
		affiche.setOpaque(false);


		lancerPartie = new JScrollPane(affiche);
		lancerPartie.setBackground(Color.white);
		lancerPartie.setPreferredSize(new Dimension(800, 610));
		lancerPartie.setBorder(BorderFactory.createTitledBorder("Resultat de toutes parties"));

		//scroll=new JScrollPane(affiche);
		lancerPartie.setBounds(10,60,800,610);
		lancerPartie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		lancerPartie.setOpaque(false);
		
	    this.add(lancerPartie);
	}

	public void afficheStats(String[] stats){
		affiche.setText(affiche.getText()+"Nouvelle partie : \n");
		int i=0;
		for(String stat:stats){
			if(i%25==0 && i!=0){
				affiche.setText(affiche.getText()+"\n"+stat+"-");
			}else{
				affiche.setText(affiche.getText()+stat+"-");
			}
			i++;
		}
		affiche.setText(affiche.getText()+"\nMoyenne :\n"+modele.getMoyenneStat()+"\n");
		affiche.setEditable(false);
		affiche.setOpaque(false);
		repaint();
		validate();
	}


	@Override
	public void mettreAJour() {

		if(modele.getResetStat()){
			affiche.setText("");
			repaint();
			validate();
			modele.setResetStat(false);
		}else{
			if(modele.getNbPartieStat()>=1) {
				afficheStats(modele.runStat());
			}
		}
	}
}
