package Cueillette;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
					grille[i][j]=new JLabel("0");
				}else if(modele.getCase(i, j)==1){
					grille[i][j]=new JLabel("1");
				}else if(modele.getCase(i, j)==2){
					grille[i][j]=new JLabel("2");
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
