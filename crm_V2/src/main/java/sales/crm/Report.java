package sales.crm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.model.StylesTable;

import sales.crm.CRMLoginLogout.Status;

public class Report {
	HSSFWorkbook workbook;
	Properties property;
	public void createFile(String browserName) 
	{
		
		//Creating the sample template file.
		workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Report");
		String[] metricsHeader={"Total Test Cases","Passed","Failed"};
		String[] statusHeader={"S.No","Testcase Name","Start Time","End Time","Status"};
		int rowCount =0;
		Row row = sheet.createRow(rowCount++);    
		Cell cell;
		int columnCount = 0;	
		row.createCell(columnCount++).setCellValue("Project Name");
		row.createCell(columnCount).setCellValue("HRMS Application");
		columnCount = 0;	
		row = sheet.createRow(rowCount++); 
		row.createCell(columnCount++).setCellValue("Browser Name");
		row.createCell(columnCount++).setCellValue(browserName);
		
		row = sheet.createRow(rowCount++); 
		row = sheet.createRow(rowCount++); 
		columnCount = 0;	
		
		CellStyle style = sheet.getWorkbook().createCellStyle();
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        
        CellStyle styleBorder = sheet.getWorkbook().createCellStyle();
        styleBorder.setBorderBottom(BorderStyle.THIN);
        styleBorder.setBorderTop(BorderStyle.THIN);
        styleBorder.setBorderLeft(BorderStyle.THIN);
        styleBorder.setBorderRight(BorderStyle.THIN);
		
        for(int i=0;i<metricsHeader.length;i++)
        {
        	cell=row.createCell(columnCount++);
            cell.setCellStyle(style);
            cell.setCellValue(metricsHeader[i]);
            sheet.autoSizeColumn(cell.getColumnIndex());
        }
		
		columnCount = 0;	
		row = sheet.createRow(rowCount++); 
		for(int i=0;i<metricsHeader.length;i++)
        {
			cell=row.createCell(columnCount++);
	        cell.setCellStyle(styleBorder);
        }
		
		sheet.createRow(rowCount++); 
		sheet.createRow(rowCount++);
		 
		row = sheet.createRow(rowCount++); 
		columnCount = 0;	
		
		for(int i=0;i<statusHeader.length;i++)
		{
			cell=row.createCell(columnCount++);
			cell.setCellStyle(styleBorder);
		    cell.setCellValue(statusHeader[i]);
		}
				
		workbook.createSheet("TestCase_Steps");
		writeToFile();	
		
	}
	
	public void testCaseMetrics(int total,int passed,int failed)
	{
		
		//writing test metrics to there respective cells.
		try{
			 property=new Properties();
				try {
					FileInputStream objFile = new FileInputStream("config.properties");
					property.load(objFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			FileInputStream inputStream = new FileInputStream(property.getProperty("resultFileName")); 
			workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet=workbook.getSheet("Report");
			int rowNum=sheet.getLastRowNum();
			for (int i = 0; i <= rowNum; i++) {
	        	HSSFRow row = sheet.getRow(i);
				if((row.getCell(0)!=null) && (row.getCell(0).toString().equalsIgnoreCase("Total Test Cases")))
				{
					row = sheet.getRow(i+1);
					Cell cell=row.getCell(0);
					cell.setCellValue(total);
					cell=row.getCell(1);
					cell.setCellValue(passed);
					cell=row.getCell(2);
					cell.setCellValue(failed);
					break;
				}
			}
			
			writeToFile();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public void testcaseStatus(ArrayList<Object> resultArray) 
	{
		
		//Writing the test status details to their respective cells.
		try{
			 property=new Properties();
				try {
					FileInputStream objFile = new FileInputStream("config.properties");
					property.load(objFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		FileInputStream inputStream = new FileInputStream(property.getProperty("resultFileName")); 
		workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet=workbook.getSheet("Report");
		int rowNum=sheet.getLastRowNum();
		int colNum=0;
		int columnCount=0;
		int sno=0;
		int columnIndex=0; 
        
		CellStyle style1 = sheet.getWorkbook().createCellStyle();
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBorderTop(BorderStyle.THIN);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        
        CellStyle styleRed = sheet.getWorkbook().createCellStyle();
        styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
        styleRed.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
        
        CellStyle styleGreen = sheet.getWorkbook().createCellStyle();
        styleGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		
        //Finding the row header of status table so that the S.no is calculated by getLatRowNum-rowNoOfSNo
        for (int i = 0; i <= rowNum; i++) {
        	HSSFRow tempRow = sheet.getRow(i);
			if((tempRow.getCell(0)!=null) && (tempRow.getCell(0).toString().equalsIgnoreCase("S.No")))
			{
				sno=i;
				break;
			}
		}
        
        HSSFRow row = sheet.createRow(rowNum+1);
		rowNum++;
		columnCount = 0;	
		for(int i=0;i<5;i++)
		{
			Cell cell=row.createCell(columnCount++);
			cell.setCellStyle(style1);
		}
		
		colNum=sheet.getRow(rowNum-1).getLastCellNum();
		
		//Getting each value from the array list which consist of test case status result and placing in the cell.
		for (int j = 0; j < colNum; j++)
		{
			if(j==0)
				row.getCell(j).setCellValue(row.getRowNum()-sno);
			else
			{
				row.getCell(j).setCellValue(resultArray.get(j-1).toString());
				if(j==colNum-1 && resultArray.get(j-1).toString().equalsIgnoreCase("PASS"))
				{
					row.getCell(j).setCellStyle(styleGreen);
				}
				else if(j==colNum-1 && resultArray.get(j-1).toString().equalsIgnoreCase("FAIL"))
				{
					row.getCell(j).setCellStyle(styleRed);
				}
				columnIndex= row.getCell(j).getColumnIndex();
		        sheet.autoSizeColumn(columnIndex);
			}
		}		 		
		writeToFile();	
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testCaseStepsHeader(String testName)
	{
		
		try{
			 property=new Properties();
				try {
					FileInputStream objFile = new FileInputStream("config.properties");
					property.load(objFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			FileInputStream inputStream = new FileInputStream(property.getProperty("resultFileName")); 
			workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet=workbook.getSheet("TestCase_Steps");
			String[] headerNames={"Step No","Description","Expected Result","Actual Result","Status"};
			int rowNum=sheet.getLastRowNum();
			if(rowNum!=0)
				sheet.createRow(++rowNum);
			Row row = sheet.createRow(++rowNum);
			rowNum++;
			row.createCell(0).setCellValue(testName);
			int columnIndex = row.getCell(0).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);
			        
	        CellStyle styleGrey = sheet.getWorkbook().createCellStyle();
	        styleGrey.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        styleGrey.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        int cellNum = 0;
	        row = sheet.createRow(rowNum++);
	        Cell cell=row.createCell(cellNum++);
	        for(int i=0;i<headerNames.length;i++)
	        {
	        	cell=row.createCell(cellNum++);
		        cell.setCellStyle(styleGrey);
		        cell.setCellValue(headerNames[i]);
	        }     
	        
			writeToFile();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testCaseSteps(String description,String exceptedResult, String actualResult, Status Status)
	{
		try{
			 property=new Properties();
				try {
					FileInputStream objFile = new FileInputStream("config.properties");
					property.load(objFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			FileInputStream inputStream = new FileInputStream(property.getProperty("resultFileName")); 
			workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet=workbook.getSheet("TestCase_Steps");
			int rowNum=sheet.getLastRowNum();			
			int sno = 0;
			for(int i=rowNum;i>0;i--)
			{
				HSSFRow tempRow = sheet.getRow(i);
				if((tempRow.getCell(0)!=null) && (tempRow.getCell(1).toString().equalsIgnoreCase("Step No")))
				{
					sno=i;
					break;
				}
			}
			Row row = sheet.createRow(++rowNum);
			
			int cellNum = 0;
			row.createCell(cellNum++);
			Cell cell = row.createCell(cellNum++);
		    cell.setCellValue(sheet.getLastRowNum()-sno);
            sheet.autoSizeColumn(cell.getColumnIndex());
            
            cell = row.createCell(cellNum++);
		    cell.setCellValue(description);
            sheet.autoSizeColumn(cell.getColumnIndex());
            
            cell = row.createCell(cellNum++);
		    cell.setCellValue(exceptedResult);
            sheet.autoSizeColumn(cell.getColumnIndex());
            
            cell = row.createCell(cellNum++);
		    cell.setCellValue(actualResult);
            sheet.autoSizeColumn(cell.getColumnIndex());
            
            cell = row.createCell(cellNum++);
		    cell.setCellValue(Status.toString());
            sheet.autoSizeColumn(cell.getColumnIndex());
            
            writeToFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeToFile()
	{
		try {
			 property=new Properties();
				try {
					FileInputStream objFile = new FileInputStream("config.properties");
					property.load(objFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			FileOutputStream outputStream = new FileOutputStream(property.getProperty("resultFileName")); 
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
