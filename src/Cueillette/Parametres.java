package Cueillette;

import Deplacement.DeplacementAlea;
import Deplacement.DeplacementLevy;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Parametres extends JDialog{

	private JTextField alphaLevy;
	private JTextField nLevy;
	private JTextField dAlea;
	private JTextField distPaquets;
	private JTextField densPaquets;

	public Parametres(){
		super((JFrame) null, "parametres", true);
		this.setSize(700, 410);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		setVisible(true);
	}

	private void initComponent(){

		JPanel volLevy = createJPanel("Vol de Levy");
		volLevy = addJTextArea(volLevy, "formule : \nsomme = n * GaussienA / (|GaussienB| ^ 1/alpha) \ndistance = somme / (n ^ 1/alpha)");		
		volLevy = addJLabel(volLevy, "alpha : ");
		alphaLevy = createField(DeplacementLevy.getAlpha()+"");
		volLevy.add(alphaLevy);	
		volLevy = addJLabel(volLevy, "n : ");
		nLevy = createField(DeplacementLevy.getN()+"");
		volLevy.add(nLevy);
		
		JPanel aleatoire = createJPanel("Déplacement aléatoire");
		aleatoire = addJTextArea(aleatoire, "formule : \nGaussien * d");
		aleatoire = addJLabel(aleatoire, "d : ");
		dAlea = createField(DeplacementAlea.getDAlea() +"");
		aleatoire.add(dAlea);
		
		JPanel paquets = createJPanel("Répartiton des  paquets");
		paquets = addJLabel(paquets, "diamètre en case : ");
		distPaquets = createField(Monde.getDistance() +"");
		paquets.add(distPaquets);
		paquets = addJLabel(paquets, "nombre ~ de points/paquet : ");
		densPaquets = createField(Monde.getDensite() +"");
		paquets.add(densPaquets);
		
		JPanel defaut = createJPanel("Défaut");
		defaut.setPreferredSize(new Dimension(640, 60));
		defaut = addJLabel(defaut, "rétablir les valeurs par défaut: ");
		JButton retablir = new JButton("rétablir");
		retablir.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		        alphaLevy.setText(DeplacementLevy.getAlphaDefaut() +"");
		        nLevy.setText(DeplacementLevy.getNDefaut() +"");
		        dAlea.setText(DeplacementAlea.getDDefaut() +"");
		        distPaquets.setText(Monde.getDistanceDefaut() +"");
		        densPaquets.setText(Monde.getDensiteDefaut() +"");
		      }
		});
		retablir.setPreferredSize(new Dimension(100, 25));
		defaut.add(retablir);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(volLevy);
		content.add(aleatoire);
		content.add(paquets);
		content.add(defaut);
		
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				try{
					DeplacementLevy.setAlpha(getDouble(alphaLevy, "Vol de Levy - alpha"));
					DeplacementLevy.setN(getInt(nLevy, "Vol de Levy - n"));
					DeplacementAlea.setdAlea(getInt(dAlea, "Déplacement aléatoire - d"));
					Monde.setDistance(getInt(distPaquets, "Répartition paquets - distance"));
					Monde.setDensite(getInt(densPaquets, "Répartition paquets - densité"));
					setVisible(false);
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Valeur invalide dans le champ suivant: "+nfe.getLocalizedMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    
	    JPanel control = new JPanel();
	    control.add(okBouton);
	    control.add(cancelBouton);

	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	private JPanel createJPanel(String titre){
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(320, 110));
		panel.setBorder(BorderFactory.createTitledBorder(titre));
		return panel;
	}
	
	private JPanel addJTextArea(JPanel panel, String titre){
		JTextArea textArea = new JTextArea(titre);
		textArea.setPreferredSize(new Dimension(300, 50));
		textArea.setEditable(false);
		textArea.setOpaque(false);
		panel.add(textArea);
		return panel;
	}
	
	private JPanel addJLabel(JPanel panel, String titre){
		JLabel label = new JLabel(titre);
		panel.add(label);
		return panel;
	}
	
	private JTextField createField(String string) {
		JTextField field = new JTextField(string);
		field.setPreferredSize(new Dimension(100, 25));
		return field;
	}
	
	private double getDouble(JTextField champ, String erreur){
		double a=Double.parseDouble(champ.getText());
		if(a<=0)
			throw new NumberFormatException(erreur);
		return a ;
	}
	
	private int getInt(JTextField champ, String erreur){
		int n=Integer.parseInt(champ.getText());
		if(n<=0)
			throw new NumberFormatException(erreur);
		return n;
	}
}
