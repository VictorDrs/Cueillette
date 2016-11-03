package Cueillette;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class LancerGUI extends JFrame {
	
	public LancerGUI(){
		super("Cueillette exploratoire");
		

		setPreferredSize(new Dimension(1000,500));
		Modele modele=new Modele();
		VueControl vc=new VueControl(modele);
		VueScore vs=new VueScore(modele);
		VuePlateau vp=new VuePlateau(modele);
		Menu men=new Menu(modele);
		modele.ajouterVue(vc);
		modele.ajouterVue(vs);
		modele.ajouterVue(vp);
		
		add(vs,BorderLayout.SOUTH);
		add(vp,BorderLayout.CENTER);
		add(vc,BorderLayout.EAST);
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
