package Cueillette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueControl extends JPanel implements Vue {
	protected Modele modele;
	protected JButton start;
	protected JButton stop;
	protected JButton step;
	
	
	public VueControl(Modele mod){
		super();
		modele=mod;
		
		
		start=new JButton("Start");
		stop=new JButton("Stop");
		step=new JButton("Step");
		
		start.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//TODO modele.start();
			}
		});
		stop.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//TODO modele.stop();
			}
		});
		step.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				//TODO modele.step();
			}
		});
		
		this.add(start);
		this.add(stop);
		this.add(step);
		
	}
	public void mettreAJour() {
		// TODO Auto-generated method stub
		
	}

}
