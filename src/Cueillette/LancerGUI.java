package Cueillette;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LancerGUI extends JFrame {
	
	public LancerGUI(){
		super();
		
		setPreferredSize(new Dimension(1000,400));
		int[][] tab =  {{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,2,0,0,0,0,0},
						{0,0,0,0,0,1,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0}};
		Modele modele=new Modele(tab);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		new LancerGUI();
	}
	
}
