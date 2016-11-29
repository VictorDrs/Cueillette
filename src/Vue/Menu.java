package Vue;

import Cueillette.Modele;
import Cueillette.Parametres;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
		private final Modele modele;
		
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
					new Parametres();
				}
			});
			
			switchAffichage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modele.setSwitchAffichage(true);
					modele.majVues();
				}
			});
			
			menu.add(ouvrir);
			menu.add(parametres);
			menu.add(switchAffichage);
			
			this.add(menu);
		}
}
