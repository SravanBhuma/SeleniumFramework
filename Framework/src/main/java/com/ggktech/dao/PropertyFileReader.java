package com.ggktech.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

import com.framework.utils.PathConstants;

public class PropertyFileReader 
{
	String path = System.getProperty("user.dir");
	private String propertyFilePath;
	public Properties prop;
	FileInputStream objfile;

	public String LoadProperties(String FileName, String Key)
	{
		try
		{
			propertyFilePath= path+ File.separator +PathConstants.RESOURCE_PATH+ File.separator +FileName;
			System.out.println(propertyFilePath);
			objfile = new FileInputStream(propertyFilePath);
			prop = new Properties();
			prop.load(objfile);
		}
		catch(Exception e)
		{
			System.out.println(FileName+" file is not loaded properly");
			System.out.println("Message is:"+e.getMessage());
			System.out.println("Cause is:"+e.getCause());
			e.printStackTrace();
		}
		return	prop.getProperty(Key);
	}

	public By byLocator(String FileName, String Key)
	{
		String value = LoadProperties(FileName, Key);
		return locatorParser(value);
	}

	public By locatorParser(String locator) 
	{
		By loc = By.id(locator);
		if (locator.contains("By.id"))
			loc = By.id(locator.substring(locator.indexOf("\"")+1,
					locator.length() - 2));
		else if (locator.contains("By.name"))
			loc = By.name(locator.substring(locator.indexOf("\"")+1,
					locator.length() - 2));
		if (locator.contains("By.xpath"))
			loc = By.xpath(locator.substring(locator.indexOf("\"")+1 ,
					locator.length() - 2));
		return loc;

	}
}
