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
	int x,y;
	public VuePlateau(Modele mod){
		super();
		modele=mod;
		changeSize();
	}
	
	
	public void changeSize(){
		this.removeAll();
		x=modele.getSizeX();
		y=modele.getSizeY();
		grille=new JLabel[modele.getSizeX()][modele.getSizeY()];
		for(int i=0;i<modele.getSizeX();i++){
			for(int j=0;j<modele.getSizeY();j++){
				if(modele.getCase(i, j)==0){
					grille[i][j]=new JLabel(new ImageIcon(getClass().getResource("/resources/vide.png")));
				}else if(modele.getCase(i, j)==1){
					grille[i][j]=new JLabel(new ImageIcon(getClass().getResource("/resources/interet.png")));
				}else if(modele.getCase(i, j)==2){
					grille[i][j]=new JLabel(new ImageIcon(getClass().getResource("/resources/agent.png")));
				}
				this.add(grille[i][j]);
			}
		}
		for(int i=0;i<modele.getSizeX();i++){
			for(int j=0;j<modele.getSizeY();j++){
				grille[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
			}
		}
		setLayout(new GridLayout(modele.getSizeX(),modele.getSizeY()));
	}
	@Override
	public void mettreAJour() {
		if(x!=modele.getSizeX() || y!=modele.getSizeX()){
			changeSize();
		}
		for(int i=0;i<modele.getSizeX();i++){
			for(int j=0;j<modele.getSizeY();j++){
				if(modele.getCase(i, j)==0){
					grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/vide.png")));
				}else if(modele.getCase(i, j)==1){
					grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/interet.png")));
				}else if(modele.getCase(i, j)==2){
					grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/agent.png")));
				}else if(modele.getCase(i, j)==3){
					grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/trace.png")));
				}
				this.add(grille[i][j]);
			}
		}

	}

}
