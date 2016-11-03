package Cueillette;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VuePlateau extends JPanel implements Vue {
	protected final Modele modele;
	protected JLabel[][] grille;
	protected int x,y;
	protected boolean[][] memoire;
	protected boolean affichage;

	public VuePlateau(Modele mod){
		super();
		modele=mod;
		memoire=new boolean[modele.getSizeX()][modele.getSizeY()];
		affichage = false;
		changeSize();
	}

	public void changeAffichage(){
		if(affichage){
			changeSize();
		}
		else{
			removeAll();
			repaint();
		}
		affichage = !affichage ;
	}

	public void changeSize(){
		this.removeAll();
		x=modele.getSizeX();
		y=modele.getSizeY();
		memoire=new boolean[x][y];
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
				grille[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}
		setLayout(new GridLayout(modele.getSizeX(),modele.getSizeY()));
	}

	public void redraw(){
		int temp;
		for(int i=0;i<modele.getSizeX();i++){
			for(int j=0;j<modele.getSizeY();j++){
				temp=modele.getCase(i, j);
				if(temp==2){
					grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/agent.png")));
					memoire[i][j]=false;
				}else if(temp==3){
					if(!memoire[i][j]){
						grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/trace.png")));
						memoire[i][j]=true;
					}
				}
			}
		}
	}
	@Override
	public void mettreAJour() {
		if(modele.switchAffichage){
			changeAffichage();
			modele.switchAffichage = false;
		}
		else{
			if(x!=modele.getSizeX() || y!=modele.getSizeX()){
				changeSize();
			}
			if(modele.getNews()){
				int temp=-1;
				for(int i=0;i<modele.getSizeX();i++){
					for(int j=0;j<modele.getSizeY();j++){
						temp=modele.getCase(i, j);
						if(temp==0){
							grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/vide.png")));
						}else if(temp==1){
							grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/interet.png")));
						}else if(temp==2){
							grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/agent.png")));
						}else if(temp==3){
							grille[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/trace.png")));
						}
					}
				}
				modele.setNews(false);
			}else{
				redraw();
			}
		}
	}
}
