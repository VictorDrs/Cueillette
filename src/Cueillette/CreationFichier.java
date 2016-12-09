package Cueillette;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreationFichier {

	public CreationFichier() {

	}

	public void ajouterParametres(){
		//recuperer para modele
		//recup para deplacements
		//add listeParametres
	}

	public void enregistrer(String chemin){
		Workbook wb = new XSSFWorkbook();
		String safeName = WorkbookUtil.createSafeSheetName("[Statistiques*?]");
		Sheet sheet = wb.createSheet(safeName);
		CreationHelper createHelper = wb.getCreationHelper();

		if(Statistiques.donnees != null && Statistiques.donnees.size()!=0){

			Row row1 = sheet.createRow((short)0);
			Row row2 = sheet.createRow((short)1);
			Row row3 = sheet.createRow((short)2);
			Row row4 = sheet.createRow((short)3);
			Row row6 = sheet.createRow((short)5);
			Row row7 = sheet.createRow((short)6);
			Row row8 = sheet.createRow((short)7);
			Row row9 = sheet.createRow((short)8);

			for(int i=0; i<Statistiques.donnees.size(); i++){
				int col=i*10-1;
				if(col<1)col=0;
				String deplacement="";
				switch(Statistiques.donnees.get(i).getMode()){
				case 1:deplacement="aleatoire";break;
				case 2:deplacement="vol de levy";break;
				case 3:deplacement="personnalisé";break;
				default: deplacement="non defini";break;
				}


				row1.createCell(col).setCellValue(createHelper.createRichTextString("nombre parties"));
				row1.createCell(col+1).setCellValue(Statistiques.donnees.get(i).getNbPartie());
				row1.createCell(col+2).setCellValue(createHelper.createRichTextString("deplacement"));
				row1.createCell(col+3).setCellValue(createHelper.createRichTextString(deplacement));

				row2.createCell(col).setCellValue(createHelper.createRichTextString("agents"));
				row2.createCell(col+1).setCellValue(Statistiques.donnees.get(i).getNbAgent());
				row2.createCell(col+2).setCellValue(createHelper.createRichTextString("points d'interet"));
				row2.createCell(col+3).setCellValue(Statistiques.donnees.get(i).getNbInteret());
				row2.createCell(col+4).setCellValue(createHelper.createRichTextString("taille grille"));
				row2.createCell(col+5).setCellValue(Statistiques.donnees.get(i).getTaille());

				row3.createCell(col).setCellValue(createHelper.createRichTextString("alpha - levy"));
				row3.createCell(col+1).setCellValue(Statistiques.donnees.get(i).getAlphaLevy());
				row3.createCell(col+2).setCellValue(createHelper.createRichTextString("n - levy"));
				row3.createCell(col+3).setCellValue(Statistiques.donnees.get(i).getnLevy());
				row3.createCell(col+4).setCellValue(createHelper.createRichTextString("d - alea"));
				row3.createCell(col+5).setCellValue(Statistiques.donnees.get(i).getdAlea());

				row4.createCell(col).setCellValue(createHelper.createRichTextString("diametre - points interet"));
				row4.createCell(col+1).setCellValue(Statistiques.donnees.get(i).getDistPaquets());
				row4.createCell(col+2).setCellValue(createHelper.createRichTextString("densite - points interet"));
				row4.createCell(col+3).setCellValue(Statistiques.donnees.get(i).getDensPaquets());

				//insertion du nombre de pas de chaque partie
				int[] nbPas = Statistiques.donnees.get(i).getNbPas();
				for(int j=0; j<nbPas.length; j++){
					int ligne = j+6;
					if(sheet.getRow(ligne) != null)sheet.getRow(ligne).createCell(col).setCellValue(nbPas[j]);
					else sheet.createRow((short)ligne).createCell(col).setCellValue(nbPas[j]);
				}

				Cell first = sheet.getRow(6).getCell(col);
				Cell last = sheet.getRow(nbPas.length-1+6).getCell(col);
				
				row6.createCell(col+2).setCellValue(createHelper.createRichTextString("moyenne"));
				row6.createCell(col+3).setCellFormula("AVERAGE("
						+CellReference.convertNumToColString(first.getColumnIndex())+(first.getRowIndex()+1)+":"
						+CellReference.convertNumToColString(last.getColumnIndex())+(last.getRowIndex()+1)+")");

				row7.createCell(col+2).setCellValue(createHelper.createRichTextString("min"));
				row7.createCell(col+3).setCellFormula("MIN("
						+CellReference.convertNumToColString(first.getColumnIndex())+(first.getRowIndex()+1)+":"
						+CellReference.convertNumToColString(last.getColumnIndex())+(last.getRowIndex()+1)+")");
				
				row8.createCell(col+2).setCellValue(createHelper.createRichTextString("max"));
				row8.createCell(col+3).setCellFormula("MAX("
						+CellReference.convertNumToColString(first.getColumnIndex())+(first.getRowIndex()+1)+":"
						+CellReference.convertNumToColString(last.getColumnIndex())+(last.getRowIndex()+1)+")");
				
				row9.createCell(col+2).setCellValue(createHelper.createRichTextString("mediane"));
				row9.createCell(col+3).setCellFormula("MEDIAN("
						+CellReference.convertNumToColString(first.getColumnIndex())+(first.getRowIndex()+1)+":"
						+CellReference.convertNumToColString(last.getColumnIndex())+(last.getRowIndex()+1)+")");
				
				sheet.autoSizeColumn(col);
				sheet.autoSizeColumn(col+1);
				sheet.autoSizeColumn(col+2);
				sheet.autoSizeColumn(col+3);
				sheet.autoSizeColumn(col+4);
				sheet.autoSizeColumn(col+5);
			}
		}


		try(FileOutputStream fileOut = new FileOutputStream(chemin);) {
			wb.write(fileOut);
			wb.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
