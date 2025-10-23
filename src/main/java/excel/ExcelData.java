package excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	private static XSSFSheet sheet;
	private DataFormatter format;

	public ExcelData(String filename) throws IOException {
		this.format = new DataFormatter();
		filename = (filename == null || filename.isEmpty() || filename.isBlank())
			? "src/main/resources/DemoData.xlsx"
				: filename;
		XSSFWorkbook workbook = new XSSFWorkbook(filename);
		sheet = workbook.getSheet("Sheet1");
	}

	public ExcelData(String filename, String sheetName) throws IOException {
		this.format = new DataFormatter();
		filename = (filename == null || filename.isEmpty() || filename.isBlank())
			? "src/main/resources/DemoData.xlsx"
				: filename;
		sheetName = (sheetName == null || sheetName.isEmpty() || sheetName.isBlank())
				? "Sheet1"
				: sheetName;
		XSSFWorkbook workbook = new XSSFWorkbook(filename);
		sheet = workbook.getSheet(sheetName);
	}

	public int getColumnNo_OfString_InRowNo(String str, int rowNo) {
		Row row = sheet.getRow(rowNo);
		Iterator<Cell> cellIterator = row.iterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (this.format.formatCellValue(cell).equals(str)) {
				return cell.getColumnIndex();
			}
		}
		return -1;
	}
	
	public int getRowNo_OfString_InColumnNo(String str, int colNo) { // return the row no of testcase purchase.
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			Cell cell = rowIterator.next().getCell(colNo);
			if (this.format.formatCellValue(cell).equals(str)) {
				return cell.getRowIndex();
			}
		}
		return -1;
	}

	public List<String> getCellValues_OfRowNo(int rowNo) {
		Row row = sheet.getRow(rowNo);
		List<String> list = new ArrayList<>();
		Iterator<Cell> cellIterator = row.iterator();

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			list.add(this.format.formatCellValue(cell));
		}
		return list;
	}

}
