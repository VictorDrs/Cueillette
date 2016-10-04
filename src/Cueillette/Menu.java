package Cueillette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
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
			
			ButtonGroup repartGroup= new ButtonGroup();
			JRadioButtonMenuItem repartAlea=new JRadioButtonMenuItem("Répartition aléatoire");
			JRadioButtonMenuItem repartPaquet=new JRadioButtonMenuItem("Répartition par paquets");
			
			JMenuItem nouveau=new JMenuItem("Nouvelle grille");
			JMenuItem size=new JMenuItem("Modifier la taille");
			JMenuItem ouvrir=new JMenuItem("Ouvrir");
			JMenuItem relancer=new JMenuItem("Relancer");
			JMenuItem nagents=new JMenuItem("Modifier le nombre d'agents");
			JMenuItem npatchs=new JMenuItem("Modifier le nombre de patchs");
			
			
			volLevy.setSelected(true);
			volLevy.setActionCommand("Vol de levy");
			
			alea.setActionCommand("Aleatoire");
			
			repartAlea.setSelected(true);
			repartAlea.setActionCommand("Répartition aléatoire");
			
			repartPaquet.setActionCommand("Répartition par paquets");
			
			nouveau.setActionCommand("Nouvelle grille");
			size.setActionCommand("Modifier la taille");
			ouvrir.setActionCommand("Ouvrir");
			relancer.setActionCommand("Relancer");
			nagents.setActionCommand("Modifier le nombre d'agents");
			npatchs.setActionCommand("Modifier le nombre de patchs");
			
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
						modele.changeSize(JOptionPane.showInputDialog("Largeur de la grille"));
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});

			repartAlea.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					modele.setRepartition(false);
					modele.majVues();
					
				}
			});
			
			repartPaquet.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					modele.setRepartition(true);
					modele.majVues();
					
				}
			});
			
			ouvrir.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser jf = new JFileChooser();
						int reponse = jf.showOpenDialog(getParent());
						if (reponse == JFileChooser.APPROVE_OPTION){
							File  fich = jf.getSelectedFile();
							modele.ouvrir(fich.getAbsolutePath());
						}
					
						}catch (Exception ex){
							ex.printStackTrace();
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
			
			nagents.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						modele.nAgents(JOptionPane.showInputDialog("Nombre d'agents"));
					}catch(Exception NumberFormatException){
						JOptionPane.showMessageDialog(null,"Entrez un nombre positif","Alerte",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			npatchs.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						modele.nPatchs(JOptionPane.showInputDialog("Nombre de patchs"));
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
			repartGroup.add(repartAlea);
			repartGroup.add(repartPaquet);
			menu.add(repartAlea);
			menu.add(repartPaquet);
			
			menu.addSeparator();
			menu.add(size);
			menu.add(nagents);
			menu.add(npatchs);
			
			menu.addSeparator();
			menu.add(nouveau);
			menu.add(relancer);
			menu.add(ouvrir);
			
			this.add(menu);
		}
}
