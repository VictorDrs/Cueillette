package Cueillette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
		protected Modele modele;
		
		public Menu(Modele mod){
			super();
			
			modele=mod;
			
			JMenu menu=new JMenu("Options");
			menu.setMnemonic(KeyEvent.VK_O);
			
			JMenuItem ouvrir=new JMenuItem("Ouvrir");
			JMenuItem parametres=new JMenuItem("Paramètres");
			JMenuItem switchAffichage=new JMenuItem("Changer d'affichage");
			
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
			
			parametres.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					new Parametres(null, "parametres", true);
				}
			});
			
			switchAffichage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modele.switchAffichage = true ;
					modele.majVues();
				}
			});
			
			menu.add(ouvrir);
			menu.add(parametres);
			menu.add(switchAffichage);
			
			this.add(menu);
		}
}
