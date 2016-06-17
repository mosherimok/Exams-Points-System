package ui_settings;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import utility_dialogs.DialogProcessLoading;

public class ExcelStudentsExtracter {
	
	private final int COLUMNS = 5;
	
	private Workbook wb;
	private Sheet sheet;
	private Object[][] students;

	public ExcelStudentsExtracter(String filePath) throws EncryptedDocumentException, InvalidFormatException, IOException{
		initWorkBookAndSheet(new File(filePath));
	}
	
	public ExcelStudentsExtracter(File file) throws EncryptedDocumentException, InvalidFormatException, IOException{
		initWorkBookAndSheet(file);
	}
	
	public void extract(){
		
		int rows = sheet.getPhysicalNumberOfRows();
		
		DialogProcessLoading bar = new DialogProcessLoading(rows);
		
		students = new Object[rows][COLUMNS];

		Row row;
		Cell cell;
		for(int r = 0;r<rows;r++){
			row = sheet.getRow(r);
			for(int c=0;c<COLUMNS;c++){
				cell = row.getCell(c);
				if(cell!=null){
					cell.setCellType(Cell.CELL_TYPE_STRING);
					students[r][c] = cell.getStringCellValue();
				}
			}
			bar.increaseProgressBy1();
		}
		
	}
	
	public Object[][] getAllStudents(){
		return students;
	}
	
	private void initWorkBookAndSheet(File file) throws EncryptedDocumentException, InvalidFormatException, IOException{
		if(file==null)
			return;
		
		wb = WorkbookFactory.create(file);
		sheet = wb.getSheetAt(0);
	}

}
