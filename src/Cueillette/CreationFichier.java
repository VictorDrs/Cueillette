package Cueillette;

import java.io.FileOutputStream;
import java.util.ArrayList;
/*
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/

public class CreationFichier {

	ArrayList<String[]> listeParametres;
	String[] parametres;

	public CreationFichier() {
		
	}

	public void ajouterParametres(){
		//recuperer para modele
		//recup para deplacements
		//add listeParametres
	}

	public void enregistrer(String chemin){
		/*
		Workbook wb = new XSSFWorkbook();
		String safeName = WorkbookUtil.createSafeSheetName("[Statistiques*?]");
		Sheet sheet = wb.createSheet(safeName);
		Row row = sheet.createRow((short)0);
		CreationHelper createHelper = wb.getCreationHelper();
		
		row.createCell(2).setCellValue(
			createHelper.createRichTextString("This is a string")
		);
		
		try(FileOutputStream fileOut = new FileOutputStream(chemin);) {
			wb.write(fileOut);
			wb.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		*/

	}

}
