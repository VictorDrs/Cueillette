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
	protected final Modele modele;
	protected final JButton start;
	protected final JButton stop;
	protected final JButton step;
	protected final JRadioButton levy;
	protected final JRadioButton alea;
	protected final JRadioButton interetAlea;
	protected final JRadioButton interetPaquet;
	protected final JButton taille;
	protected final JButton nbAgent;
	protected final JButton nbInteret;
	protected final JButton newGrid;
	protected final JButton relancer;
	
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
		
		
		start.setMnemonic(KeyEvent.VK_S);
		stop.setMnemonic(KeyEvent.VK_T);
		newGrid.setMnemonic(KeyEvent.VK_N);
		step.setMnemonic(KeyEvent.VK_E);
		nbInteret.setMnemonic(KeyEvent.VK_I);
		nbAgent.setMnemonic(KeyEvent.VK_A);
		relancer.setMnemonic(KeyEvent.VK_R);
		taille.setMnemonic(KeyEvent.VK_L);
		
		
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
			}
		});
		alea.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setMode(false);				
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
					JOptionPane.showMessageDialog(null,"Entrez un nombre positif (100>taille>6)","Alerte",JOptionPane.ERROR_MESSAGE);
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
			}
		});
		
		interetPaquet.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setRepartition(true);				
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
