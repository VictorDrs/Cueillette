package Cueillette;

import java.awt.BorderLayout;
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
	protected ImageIcon imgAgent;
	protected ImageIcon imgVide;
	protected ImageIcon imgInteret;
	protected ImageIcon imgTrace;
	protected Resultats resultats;

	public VuePlateau(Modele mod){
		super();
		modele=mod;
		memoire=new boolean[modele.getSizeX()][modele.getSizeY()];
		affichage = false;
		changeSize();
		imgAgent=new ImageIcon(getClass().getResource("/resources/agent.png"));
		imgVide=new ImageIcon(getClass().getResource("/resources/vide.png"));
		imgInteret=new ImageIcon(getClass().getResource("/resources/interet.png"));
		imgTrace=new ImageIcon(getClass().getResource("/resources/trace.png"));
		resultats=new Resultats();
	}

	public void changeAffichage(){
		affichage = !affichage ;
		if(!affichage){
			modele.setNews(true);
			modele.majVues();
			System.out.println("plateau");
		}
		else{
			removeAll();
			setLayout(new BorderLayout());
			add(resultats, BorderLayout.CENTER);
			repaint();
			validate();
			System.out.println("resultats");
		}
		modele.raz();
		System.out.println("affichage: "+affichage);
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
					grille[i][j]=new JLabel(imgVide);
				}else if(modele.getCase(i, j)==1){
					grille[i][j]=new JLabel(imgInteret);
				}else if(modele.getCase(i, j)==2){
					grille[i][j]=new JLabel(imgAgent);
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
		for(Agent a : modele.getAgent()){
			grille[a.getMemX()][a.getMemY()].setIcon(imgTrace);
			grille[a.getX()][a.getY()].setIcon(imgAgent);
		}
	}

	public void mapVide(){
            this.removeAll();
            repaint();
            changeSize();
    }

	@Override
	public void mettreAJour() {
		if(modele.switchAffichage){
			modele.switchAffichage = false;
			changeAffichage();
		}
		else if(!affichage){
			if(x!=modele.getSizeX() || y!=modele.getSizeX()){
				changeSize();
			}
			if(modele.getNews()){
                mapVide();
				modele.setNews(false);
			}else{
				redraw();
			}
		}

	}

}
