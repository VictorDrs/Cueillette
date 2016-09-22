package Cueillette;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VuePlateau extends JPanel implements Vue {
	protected Modele modele;
	protected JLabel[][] grille;
	
	public VuePlateau(Modele mod){
		super();
		modele=mod;
		grille=new JLabel[modele.getSizeX()][modele.getSizeY()];
		for(int i=0;i<modele.getSizeX();i++){
			for(int j=0;j<modele.getSizeY();j++){
				if(modele.getCase(i, j)==0){
					grille[i][j]=new JLabel(new ImageIcon("/resources/vide.png"));
				}else if(modele.getCase(i, j)==1){
					grille[i][j]=new JLabel(new ImageIcon("/resources/interet.png"));
				}else if(modele.getCase(i, j)==2){
					grille[i][j]=new JLabel(new ImageIcon("/resources/agent.png"));
				}
				this.add(grille[i][j]);
			}
		}
		for(int i=0;i<modele.getSizeX();i++){
			for(int j=0;j<modele.getSizeY();j++){
				grille[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}
		setLayout(new GridLayout(modele.getSizeX(),modele.getSizeY()));
	}
	@Override
	public void mettreAJour() {
		// TODO Auto-generated method stub

	}

}
