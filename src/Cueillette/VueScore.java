package Cueillette;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueScore extends JPanel implements Vue {
	protected Modele modele;
	protected JLabel score;
	
	
	public VueScore(Modele mod){
		super();
		modele=mod;
		
		score=new JLabel("Nombre de pas : "+modele.getNbPas());
		this.add(score);
	}
	@Override
	public void mettreAJour() {
		score.setText("Nombre de pas : "+modele.getNbPas());
	}

}
