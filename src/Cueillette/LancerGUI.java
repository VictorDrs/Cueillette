package Cueillette;

import Vue.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class LancerGUI extends JFrame {
	
	private LancerGUI(){
		super("Cueillette exploratoire");
		

		setPreferredSize(new Dimension(1000,500));
		Modele modele=new Modele();
		ChangeAffichage ca=new ChangeAffichage(modele);
		Menu men=new Menu(modele);
		modele.ajouterVue(ca);

		add(ca,BorderLayout.CENTER);
		setJMenuBar(men);
		modele.majVues();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		new LancerGUI();
	}
	
}
