package com.ggktech.service;
import org.testng.annotations.DataProvider;

import com.framework.utils.SheetConstants;
import com.ggktech.dao.ExcelReader;

public class DataProviders 
{
	public static Object[][] dataProvider(String excelPath, String sheetName)
	{
		ExcelReader excelData = new ExcelReader(excelPath,sheetName);
		int rows = excelData.getRowCount();
		int columns = excelData.getColumnCount();
		Object[][] data = new Object[rows-1][columns];

		for(int i = 1; i < rows; i++)
		{
			for (int j=0; j<columns;j++)
			{
				data[i-1][j]=excelData.getCellDataString(i, j);
			}
		}

		return data;
	}
	

	@DataProvider(name="SampleTest")
	public Object[][] dataProvider_One()
	{
		Object[][] testData = DataProviders.dataProvider(SheetConstants.TESTDATA,SheetConstants.DATAPROVIDER);
		return testData;
	}
}
