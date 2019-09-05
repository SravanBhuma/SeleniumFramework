package Practice.Framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;

public class DataBaseExample
{
	
	@Test
	public void Connection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://ggku2ser6;databasename=Sravan;integratedsecurity=true");
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("select * from AutomationForm");
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnCount = rsmd.getColumnCount();
		TreeMap<String, String> dataBaseValues = new TreeMap<>();
		while(resultSet.next())
		{
			for (int i = 1; i <= columnCount; i++) 
			{
				dataBaseValues.put(rsmd.getColumnName(i), resultSet.getString(rsmd.getColumnName(i)));
			}
		}
		for(Map.Entry<String, String> entry:dataBaseValues.entrySet())
		{
			System.out.println("Key value is: "+entry.getKey()+" and the value is: "+entry.getValue());
		}
	}

}
