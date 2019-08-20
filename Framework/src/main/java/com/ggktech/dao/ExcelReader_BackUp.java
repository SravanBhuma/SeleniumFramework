package com.ggktech.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.framework.utils.PathConstants;

public class ExcelReader_BackUp
{
	public String getExcelValues(String ExcelFileName,String SheetName, String ColumnName, int RowNumber)
	{
		String path = System.getProperty("user.dir");
		String ExcelFilePath = null;
		FileInputStream inputStream;
		Workbook workbook = null;
		Sheet sheet;
		Row row;
		String cellValue = null;
	try
	{
		ExcelFilePath= path+ File.separator +PathConstants.RESOURCE_PATH+ File.separator +ExcelFileName;
		inputStream = new FileInputStream(ExcelFilePath);
		workbook = new HSSFWorkbook(inputStream);
		sheet = workbook.getSheet(SheetName);
		row = sheet.getRow(RowNumber);
		Cell cell = getCellFromRow(row, ColumnName, sheet);
		cellValue = cell.getStringCellValue();
	}
	catch(Exception e)
	{
		System.out.println("Excel File is not readed properly : " + e.getMessage());
	}
	finally
	{
		try 
		{
			workbook.close();
		}
		catch (IOException e) 
		{
			System.out.println("Message is:"+e.getMessage());
			System.out.println("Cause is:"+e.getCause());
			e.printStackTrace();
		}
	}
	return cellValue;
	}
	
	
	public Cell getCellFromRow(Row mRow, String colName, Sheet sheet) 
	{
		Cell mCell = null;
		Row iRow = sheet.getRow(0);
		for (int i = 0; i < iRow.getLastCellNum(); i++) {
			if (iRow.getCell(i) != null && iRow.getCell(i).getStringCellValue().equals(colName)) {
				mCell = mRow.getCell(i);
				break;
			}
		}

		return mCell;
	}

}
