package Cueillette;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueScore extends JPanel implements Vue {
	protected Modele modele;
	protected JLabel score;
	protected JLabel interet;
	protected JLabel agent;
	
	
	public VueScore(Modele mod){
		super();
		modele=mod;
		
		
		score=new JLabel("Nombre de pas : "+modele.getNbPas());
		interet=new JLabel("Nombre de point restant : "+modele.getInteret());
		agent=new JLabel("Nombre d'agent(s) : "+modele.getNbAgent());
		this.add(score);
		this.add(interet);
		this.add(agent);
	}
	@Override
	public void mettreAJour() {
		score.setText("Nombre de pas : "+modele.getNbPas());
		interet.setText("Nombre de point restant : "+modele.getInteret());
		agent.setText("Nombre d'agent(s) : "+modele.getNbAgent());
	}

}
