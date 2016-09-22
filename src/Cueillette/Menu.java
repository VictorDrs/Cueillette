package Cueillette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
			
			volLevy.setSelected(true);
			volLevy.setActionCommand("Vol de levy");
			
			alea.setActionCommand("Aleatoire");
			
			
			
			
			
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
			
			
			
			
			
			
			
			
			
			
			
			group.add(volLevy);
			group.add(alea);
			menu.add(volLevy);
			menu.add(alea);
			
			
			this.add(menu);
		}
}
