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
	
	private int n;
	private JPanel lancerPartie;
	private JLabel nbPartiesLabel;
	private JTextField nbParties;
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
		lancerPartie.setBorder(BorderFactory.createTitledBorder("Lancer les parties"));
		nbPartiesLabel = new JLabel("Nombre de parties à lancer: ");
		nbParties = new JTextField();
		nbParties.setPreferredSize(new Dimension(100, 25));
		lancerPartie.add(nbPartiesLabel);
		lancerPartie.add(nbParties);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(lancerPartie);
		
		JPanel control = new JPanel();
		
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				try{
					setN(getNbParties());
					String[] s =modele.runStat(n);
					for(String ss :s ){
						System.out.println(ss);
					}
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Valeur invalide dans le champ suivant: "+nfe.getLocalizedMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	    control.add(okBouton);
		
	    this.add(content, BorderLayout.CENTER);
	    this.add(control, BorderLayout.EAST);
	}

	public void afficheStats(String[] stats){
		JTextArea affiche = new JTextArea("");
		for(String stat:stats){
			affiche.setText(affiche.getText()+"\n"+stat);
		}
		affiche.setPreferredSize(new Dimension(300, 50+15*stats.length));
		affiche.setEditable(false);
		affiche.setOpaque(false);
		lancerPartie.add(affiche);
		lancerPartie.setPreferredSize(new Dimension(500, 110+15*stats.length));
		repaint();
		validate();
	}
	
	private int getNbParties(){
		int n=Integer.parseInt(nbParties.getText());
		if(n<=0)
			throw new NumberFormatException("Nombre de parties");
		return n;
	}
	
	private void setN(int nbParties) {
		n = nbParties;
	}

	@Override
	public void mettreAJour() {

	}
}
