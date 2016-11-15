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

	private JTextArea formuleLevyLabel;
	private JTextArea formuleAleaLabel;
	private JLabel alphaLevyLabel;
	private JLabel nLevyLabel;
	private JLabel dAleaLabel;
	private JTextField alphaLevy;
	private JTextField nLevy;
	private JTextField dAlea;

	public Parametres(){
		super((JFrame) null, "paramÃ¨tres", true);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		setVisible(true);
	}

	private void initComponent(){

		JPanel volLevy = new JPanel();
		volLevy.setBackground(Color.white);
		volLevy.setPreferredSize(new Dimension(320, 110));
		volLevy.setBorder(BorderFactory.createTitledBorder("Vol de Levy"));
		
		formuleLevyLabel = new JTextArea("formule : \nsomme = n * GaussienA / (|GaussienB| ^ 1/alpha) \ndistance = somme / (n ^ 1/alpha)");
		formuleLevyLabel.setPreferredSize(new Dimension(300, 50));
		formuleLevyLabel.setEditable(false);
		formuleLevyLabel.setOpaque(false);
		volLevy.add(formuleLevyLabel);
		
		alphaLevyLabel = new JLabel("alpha : ");
		alphaLevy = new JTextField(DeplacementLevy.getAlpha() +"");
		alphaLevy.setPreferredSize(new Dimension(100, 25));
		volLevy.add(alphaLevyLabel);
		volLevy.add(alphaLevy);
		
		nLevyLabel = new JLabel("n : ");
		nLevy = new JTextField(DeplacementLevy.getN() +"");
		nLevy.setPreferredSize(new Dimension(100, 25));
		volLevy.add(nLevyLabel);
		volLevy.add(nLevy);

		JPanel aleatoire = new JPanel();
		aleatoire.setBackground(Color.white);
		aleatoire.setPreferredSize(new Dimension(320, 110));
		aleatoire.setBorder(BorderFactory.createTitledBorder("Déplacement aléatoire"));

		formuleAleaLabel = new JTextArea("formule : \nGaussien * d");
		formuleAleaLabel.setPreferredSize(new Dimension(300, 50));
		formuleAleaLabel.setEditable(false);
		formuleAleaLabel.setOpaque(false);
		aleatoire.add(formuleAleaLabel);
		
		dAleaLabel = new JLabel("d : ");
		dAlea = new JTextField(DeplacementAlea.getdAlea() +"");
		dAlea.setPreferredSize(new Dimension(100, 25));
		aleatoire.add(dAleaLabel);
		aleatoire.add(dAlea);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(volLevy);
		content.add(aleatoire);

		JPanel control = new JPanel();
		
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				try{
					DeplacementLevy.setAlpha(getAlphaLevy());
					DeplacementLevy.setN(getNLevy());
					DeplacementAlea.setdAlea(getDAlea());
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

	    control.add(okBouton);
	    control.add(cancelBouton);

	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	private double getAlphaLevy(){
		double a=Double.parseDouble(alphaLevy.getText());
		if(a<=0)
			throw new NumberFormatException("Vol de Levy - alpha");
		return a ;
	}
	
	private int getNLevy(){
		int n=Integer.parseInt(nLevy.getText());
		if(n<=0)
			throw new NumberFormatException("Vol de Levy - n");
		return n;
	}
	
	private int getDAlea(){
		int d=Integer.parseInt(dAlea.getText());
		if(d<=0)
			throw new NumberFormatException("Déplacement aléatoire - d");
		return d;
	}
}
