package Cueillette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
		protected Modele modele;
		
		public Menu(Modele mod){
			super();
			
			modele=mod;
			
			
			JMenu menu=new JMenu("Options");
			menu.setMnemonic(KeyEvent.VK_O);
			
			ButtonGroup group= new ButtonGroup();
			JRadioButtonMenuItem volLevy=new JRadioButtonMenuItem("Vol de levy");
			JRadioButtonMenuItem alea=new JRadioButtonMenuItem("Aleatoire");
			
			JMenuItem nouveau=new JMenuItem("Nouvelle grille");
			JMenuItem size=new JMenuItem("Modifier la taille");
			
			
			volLevy.setSelected(true);
			volLevy.setActionCommand("Vol de levy");
			
			alea.setActionCommand("Aleatoire");
			
			nouveau.setActionCommand("Nouvelle grille");
			size.setActionCommand("Modifier la taille");
			
			
			
			volLevy.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
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
			
			nouveau.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					modele.newMap();
					modele.majVues();
					
				}
			});
			
			size.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						modele.changeSize(JOptionPane.showInputDialog("Largeur de la grille"),JOptionPane.showInputDialog("Hauteur de la grille"));
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			
			
			
			
			
			
			
			group.add(volLevy);
			group.add(alea);
			menu.add(volLevy);
			menu.add(alea);
			menu.addSeparator();
			menu.add(nouveau);
			menu.add(size);
			
			this.add(menu);
		}
}
