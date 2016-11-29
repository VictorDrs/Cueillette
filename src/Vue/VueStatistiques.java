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
		nbPartiesLabel = new JLabel("Nombre de parties � lancer: ");
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
					afficheStats(s);
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Valeur invalide dans le champ suivant: "+nfe.getLocalizedMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton resetBouton = new JButton("Reset");
		resetBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				try{
					removeAll();
					initComponent();
					repaint();
					validate();
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Valeur invalide dans le champ suivant: "+nfe.getLocalizedMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	    control.add(okBouton);
	    control.add(resetBouton);
		
	    this.add(content, BorderLayout.CENTER);
	    this.add(control, BorderLayout.EAST);
	}

	public void afficheStats(String[] stats){
		int height = 50+15*stats.length;
		JTextArea affiche = new JTextArea("");
		affiche.setBorder(BorderFactory.createTitledBorder("R�sultat"));
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
/*
package Cueillette;

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
public class VueStatistiques extends JPanel{
	
	private int n;
	private JPanel lancerPartie;
	private JTextField nbParties;
	private Statistiques stat;
	
	public VueStatistiques(){
		super();
		this.initComponent();
	}
	
	public void initComponent(){
		lancerPartie = new JPanel();
		lancerPartie.setBackground(Color.white);
		lancerPartie.setPreferredSize(new Dimension(500, 110));
		lancerPartie.setBorder(BorderFactory.createTitledBorder("Lancer les parties"));
		stat=new Statistiques();
		JLabel nbPartiesLabel = new JLabel("Nombre de parties � lancer: ");
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
					//String[] s=modele.runStats(n);
					afficheStats(s);
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Valeur invalide dans le champ suivant: "+nfe.getLocalizedMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton resetBouton = new JButton("Reset");
		resetBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				try{
					removeAll();
					initComponent();
					repaint();
					validate();
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Valeur invalide dans le champ suivant: "+nfe.getLocalizedMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	    control.add(okBouton);
	    control.add(resetBouton);
		
	    this.add(content, BorderLayout.CENTER);
	    this.add(control, BorderLayout.EAST);
	}
	
	public void afficheStats(String[] stats){
		int height = 50+15*stats.length;
		JTextArea affiche = new JTextArea("");
		affiche.setBorder(BorderFactory.createTitledBorder("R�sultat"));
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
	
	private int getNbParties(){
		int n=Integer.parseInt(nbParties.getText());
		if(n<=0)
			throw new NumberFormatException("Nombre de parties");
		return n;
	}
	
	private void setN(int nbParties) {
		n = nbParties;
	}
	
	
	
}
*/