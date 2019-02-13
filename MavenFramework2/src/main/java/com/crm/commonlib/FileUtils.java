package com.crm.commonlib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtils 
{
	public Properties getPropertyObject() throws Throwable
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		return pObj;
	}
	
	public String getExcelData(String sheetName, int rowNum, int cellNum) throws Throwable
	{	String data;
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		try {
			data = cell.getStringCellValue();
		} catch (Throwable e) {
			data = String.valueOf(cell.getNumericCellValue());
			

		}

		wb.close();
		return data;
	}
	
	public void setExcelData(String sheetName, int rowNum, int cellNum, String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./Test Data/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.createCell(rowNum);
		cell.setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./Test Data/TestData.xlsx");
		wb.write(fos);
		wb.close();
	}
}

