package Cueillette;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class VueControl extends JPanel implements Vue {
	protected Modele modele;
	protected JButton start;
	protected JButton stop;
	protected JButton step;
	protected JRadioButton levy;
	protected JRadioButton alea;
	protected JRadioButton interetAlea;
	protected JRadioButton interetPaquet;
	protected JButton taille;
	protected JButton nbAgent;
	protected JButton nbInteret;
	protected JButton newGrid;
	protected JButton relancer;
	
	public VueControl(Modele mod){
		super();
		modele=mod;
		ButtonGroup deplacement=new ButtonGroup();
		ButtonGroup repartition=new ButtonGroup();
		start=new JButton("Start");
		stop=new JButton("Stop");
		step=new JButton("Step");
		levy=new JRadioButton("Vol de levy");
		alea=new JRadioButton("Deplacement Aleatoire");
		interetAlea=new JRadioButton("Repartition aleatoire");
		interetPaquet=new JRadioButton("Repartition par paquet");
		taille=new JButton("Changer la taille");
		nbAgent=new JButton("Nombre d'agent");
		nbInteret=new JButton("Nombre d'interet");
		newGrid=new JButton("Nouvelle grille");
		relancer=new JButton("Relancer");
		
		
		step.setMnemonic(KeyEvent.VK_S);
		
		
		start.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				modele.start();
				modele.majVues();
			}
		});
		stop.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				modele.stop();
				modele.majVues();
			}
		});
		step.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				modele.step();
				modele.majVues();
			}
		});
		levy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				modele.setMode(true);
				modele.majVues();
			}
		});
		alea.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setMode(false);
				modele.majVues();
				
			}
		});
		
		newGrid.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.newMap();
				modele.majVues();
				
			}
		});
		taille.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					modele.changeSize(JOptionPane.showInputDialog("Largeur de la grille"));
				}catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		nbAgent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					modele.nAgents(JOptionPane.showInputDialog("Nombre d'agents"));
				}catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		nbInteret.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					modele.nPatchs(JOptionPane.showInputDialog("Nombre de patchs"));
				}catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		relancer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.relancer();
				modele.majVues();
			}
		});
		interetAlea.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setRepartition(false);
				modele.majVues();
				
			}
		});
		
		interetPaquet.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setRepartition(true);
				modele.majVues();
				
			}
		});
		this.add(start);
		this.add(stop);
		
		deplacement.add(levy);
		deplacement.add(alea);
		this.add(levy);
		this.add(alea);
		repartition.add(interetAlea);
		repartition.add(interetPaquet);
		this.add(interetAlea);
		this.add(interetPaquet);
		this.add(step);
		this.add(taille);
		this.add(nbAgent);
		this.add(nbInteret);
		this.add(newGrid);
		this.add(relancer);
		this.setLayout(new GridLayout(6,2,1,20));
		
	}
	public void mettreAJour() {
		if(modele.getRun()){
			taille.setEnabled(false);
			levy.setEnabled(false);
			alea.setEnabled(false);
			interetAlea.setEnabled(false);
			interetPaquet.setEnabled(false);
			nbAgent.setEnabled(false);
			nbInteret.setEnabled(false);
			relancer.setEnabled(false);
			newGrid.setEnabled(false);
		}else{
			taille.setEnabled(true);
			levy.setEnabled(true);
			alea.setEnabled(true);
			interetAlea.setEnabled(true);
			interetPaquet.setEnabled(true);
			nbAgent.setEnabled(true);
			nbInteret.setEnabled(true);
			relancer.setEnabled(true);
			newGrid.setEnabled(true);
		}
		
	}

}
