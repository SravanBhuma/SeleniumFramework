package com.ggktech.dao;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.framework.utils.PathConstants;

public class ExcelReader 
{
	String ExcelFilePath = null;
	FileInputStream inputStream;
	Workbook workbook = null;
	Sheet sheet;
	Row row;
	String cellValue = null;

	
	public int getRowCount()
	{
		int rowCount = 0;
		try
		{
			rowCount = sheet.getPhysicalNumberOfRows();
		}
		catch(Exception e)
		{
			System.out.println("Message is:"+e.getMessage());
			System.out.println("Cause is:"+e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int getColumnCount()
	{
		int columnCount = 0;
		try
		{
			columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		catch(Exception e)
		{
			System.out.println("Message is:"+e.getMessage());
			System.out.println("Cause is:"+e.getCause());
			e.printStackTrace();
		}
		return columnCount;
	}
	
	public String getCellDataString(int rowNum, int colNum)
	{
		String cellValue = null;
		try
		{
			cellValue = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		}
		catch(Exception e)
		{
			System.out.println("Message is:"+e.getMessage());
			System.out.println("Cause is:"+e.getCause());
			e.printStackTrace();
		}
		return cellValue;
	}

	public ExcelReader(String ExcelFileName,String SheetName)
	{
		try
		{
			String path = System.getProperty("user.dir");
			ExcelFilePath= path+ File.separator +PathConstants.RESOURCE_PATH+ File.separator +ExcelFileName;
			inputStream = new FileInputStream(ExcelFilePath);
			workbook = new HSSFWorkbook(inputStream);
			sheet = workbook.getSheet(SheetName);
		}
		catch(Exception e)
		{
			System.out.println("Message is:"+e.getMessage());
			System.out.println("Cause is:"+e.getCause());
			e.printStackTrace();
		}
	}

	public String getExcelValues(String ColumnName, int RowNumber)
	{
		row = sheet.getRow(RowNumber);
		Cell cell = getCellFromRow(row, ColumnName, sheet);
		cellValue = cell.getStringCellValue();
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
