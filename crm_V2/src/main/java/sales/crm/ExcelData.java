package sales.crm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelData {

	public HashMap<String, String> getData(String testCase) 
	{
		Properties property=new Properties();
		try {
			FileInputStream objFile = new FileInputStream("config.properties");
			property.load(objFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream inputStream;
		HashMap<String,String> inputData=new HashMap<String,String>(); 
		try 
		{
			inputStream = new FileInputStream(property.getProperty("inputFilename"));
			Workbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet =  (HSSFSheet) workbook.getSheetAt(0);
			
			int colNum = sheet.getRow(0).getLastCellNum();
			int rowNum = sheet.getLastRowNum() + 1;
			HSSFRow rowHeader = sheet.getRow(0);
		
		    //Retrieving the cells of row obtained from the above loop.
			String colValue;
			String rowVal;
			for (int i = 1; i < rowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				if((row.getCell(0).toString().equalsIgnoreCase(testCase)))
				{
					for (int j = 1; j < colNum; j++)
					{
						HSSFCell cell = rowHeader.getCell(j);
	     	           	colValue = cell.toString();
	     	           	if(row.getCell(j)!=null)
	     	           	{
	     	           		rowVal=row.getCell(j).toString();
	     	           		inputData.put(colValue,rowVal);
	     	           	}
					}
					break;
				}
			}
		
			inputStream.close();   
	    
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	return inputData;
}
  
}
