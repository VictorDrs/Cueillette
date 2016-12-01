package Vue;

import Cueillette.Modele;
import Cueillette.Statistiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class VueStatistiques extends JPanel implements Vue{

	private JPanel lancerPartie;
	protected JTextArea affiche;
	private Modele modele;
	
	public VueStatistiques(Modele mod){
		super();
		this.initComponent();
		modele=mod;
	}
	
	public void initComponent(){
		lancerPartie = new JPanel();
		lancerPartie.setBackground(Color.white);
		lancerPartie.setPreferredSize(new Dimension(500, 110));
		lancerPartie.setBorder(BorderFactory.createTitledBorder("Resultat de toutes parties"));
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(lancerPartie);

		
	    this.add(content, BorderLayout.CENTER);
	}

	public void afficheStats(String[] stats){
		int height = 50+19*stats.length;
		affiche = new JTextArea("");
		affiche.setBorder(BorderFactory.createTitledBorder("Résultat des derniers test"));
		for(String stat:stats){
			affiche.setText(affiche.getText()+"\n"+stat);
		}
		affiche.setPreferredSize(new Dimension(300, height));
		affiche.setEditable(false);
		affiche.setOpaque(false);
		lancerPartie.add(affiche);
		lancerPartie.setPreferredSize(new Dimension(lancerPartie.getWidth(), lancerPartie.getHeight() + height));
		repaint();
		validate();
	}

	@Override
	public void mettreAJour() {
		if(modele.getResetStat()){
			lancerPartie.removeAll();
			lancerPartie.setPreferredSize(new Dimension(500, 110));
			repaint();
			validate();
			modele.setResetStat(false);
		}else{
			if(modele.getNbPartieStat()>=1)
			afficheStats(modele.runStat());
		}
	}
}