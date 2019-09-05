package sales.crm;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CheckResult extends CRMLoginLogout {
	public void verifyResult(ITestResult result)
	{
		 ArrayList<Object> resultArray=new ArrayList<Object>();
		  totalTestCases++;
		  resultArray.add(result.getTestClass().getName());
		  LocalDateTime StartTime = Instant.ofEpochMilli(result.getStartMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		  resultArray.add(StartTime);
		  LocalDateTime EndTime = Instant.ofEpochMilli(result.getEndMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		  resultArray.add(EndTime);
		  if(result.getStatus() == ITestResult.SUCCESS)
		    {
			  resultArray.add("PASS");
			  passed++;
		    }
		    else if(result.getStatus() == ITestResult.FAILURE)
		    {
				resultArray.add("FAIL");
				failed++;
		    }
		    else if(result.getStatus() == ITestResult.SKIP ){
				 resultArray.add("SKIP");
		   }
		  	Report r=new Report();
			r.testcaseStatus(resultArray);
	}
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath){
		   TakesScreenshot scrShot =((TakesScreenshot)webdriver);
	       File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	       File DestFile=new File(fileWithPath);
	       try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}
