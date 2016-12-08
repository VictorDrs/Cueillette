package Cueillette;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
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
		Row row = sheet.createRow((short)0);
		CreationHelper createHelper = wb.getCreationHelper();

		if(Statistiques.donnees != null && Statistiques.donnees.size()!=0){
			for(int i=0; i<Statistiques.donnees.size(); i++){
				int col=i*10-1;
				if(col<1)col=0;
				row.createCell(col).setCellValue(createHelper.createRichTextString("nombre parties: "+Statistiques.donnees.get(i).getNbPartie()));
				
				//insertion du nombre de pas de chaque partie
				int[] nbPas = Statistiques.donnees.get(i).getNbPas();
				for(int j=0; j<nbPas.length; j++){
					if(sheet.getRow(j+2) != null)sheet.getRow(j+2).createCell(col).setCellValue(nbPas[j]);
					else sheet.createRow((short)j+2).createCell(col).setCellValue(nbPas[j]);
				}
				
				sheet.autoSizeColumn(i*2);
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
