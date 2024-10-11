package exceldata;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import excel.ExcelData;

public class TestExcel {

	@Test
	public void main() throws IOException {
		ExcelData excelData = new ExcelData("");
		int colNo = excelData.getColumnNo_OfString_InRowNo("Testcases", 0);
		int rowNo = excelData.getRowNo_OfString_InColumnNo("Login", colNo);
		List<String> cellValues = excelData.getCellValues_OfRowNo(rowNo);
		System.out.println(cellValues);
	}

}
