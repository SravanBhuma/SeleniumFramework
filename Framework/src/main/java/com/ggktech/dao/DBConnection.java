package com.ggktech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.framework.utils.DBConstants;
import com.framework.utils.PropertyFileConstants;

public class DBConnection 
{
	Statement stmt;
	ResultSet resultSet;
	ResultSetMetaData rsmd;
	LinkedHashMap<String, String> dataBaseValues;
	ArrayList<LinkedHashMap<String, String>> dBRecordsList;
	
	PropertyFileReader dbProp = new PropertyFileReader();
	
	public DBConnection()
	{
		try
		{
		Class.forName(dbProp.LoadProperties(PropertyFileConstants.CONFIG_PROPERTIES, DBConstants.DBDRIVER));
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://"+dbProp.LoadProperties(PropertyFileConstants.CONFIG_PROPERTIES, DBConstants.DBSERVERNAME)+";databasename="+dbProp.LoadProperties(PropertyFileConstants.CONFIG_PROPERTIES, DBConstants.DBNAME)+";integratedsecurity=true");
		stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("DBConnection message is: "+e.getMessage());
			System.out.println("DBConnection cause is: "+e.getCause());
		}
	}
	
	public ArrayList<LinkedHashMap<String,String>> GetRecordsListDictionary(String Query)
	{
	try
	{
	resultSet = stmt.executeQuery(Query);
	rsmd = resultSet.getMetaData();
	int columnCount = rsmd.getColumnCount();
	dBRecordsList = new ArrayList<LinkedHashMap<String,String>>();
	while(resultSet.next())
	{
		dataBaseValues = new LinkedHashMap<>();
		for (int i = 1; i <= columnCount; i++) 
		{
			dataBaseValues.put(rsmd.getColumnName(i), resultSet.getString(rsmd.getColumnName(i)));
		}
		dBRecordsList.add(dataBaseValues);
	}
	}
	catch(Exception e)
	{
		System.out.println("DBrecord Message is: "+e.getMessage());
		System.out.println("DBrecord Cause is: "+e.getCause());
	}
	return dBRecordsList; 
	}
}
