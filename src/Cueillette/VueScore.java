package Cueillette;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueScore extends JPanel implements Vue {
	protected final Modele modele;
	protected final JLabel score;
	protected final JLabel interet;
	protected final JLabel agent;
	
	
	public VueScore(Modele mod){
		super();
		modele=mod;
		
		
		score=new JLabel("Nombre de pas : "+modele.getNbPas());
		interet=new JLabel("Nombre de point restant : "+modele.getInteret()+" ("+(modele.getInteretPourcent()+"%)"));
		agent=new JLabel("Nombre d'agent(s) : "+modele.getNbAgent());
		this.add(score);
		this.add(interet);
		this.add(agent);
	}
	@Override
	public void mettreAJour() {
		score.setText("Nombre de pas : "+modele.getNbPas());
		interet.setText("Nombre de point restant : "+modele.getInteret()+" ("+(modele.getInteretPourcent()+"%)"));
		agent.setText("Nombre d'agent(s) : "+modele.getNbAgent());
	}
}
