package Cueillette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("serial")
class Menu extends JMenuBar {
	private final Modele modele;

	public Menu(Modele mod){
		super();

		modele=mod;

		JMenu menu=new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_O);

		JMenuItem ouvrir=new JMenuItem("Ouvrir");
		JMenuItem exporter=new JMenuItem("Exporter stats");
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

		exporter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int reponse = jf.showOpenDialog(getParent());
				if (reponse == JFileChooser.APPROVE_OPTION){
					Workbook wb = new XSSFWorkbook();
					try(FileOutputStream fileOut = new FileOutputStream(jf.getSelectedFile()+"workbook.xlsx");) {
						wb.write(fileOut);
					}catch (Exception ex) {
						ex.printStackTrace();
					}
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
				modele.switchAffichage = true ;
				modele.majVues();
			}
		});

		menu.add(ouvrir);
		menu.add(exporter);
		menu.add(parametres);
		menu.add(switchAffichage);

		this.add(menu);
	}
}
