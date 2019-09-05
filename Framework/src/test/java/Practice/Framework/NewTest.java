package Practice.Framework;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.utils.ConfigConstants;
import com.framework.utils.PropertyFileConstants;
import com.framework.utils.SheetConstants;
import com.ggktech.dao.DBConnection;
import com.ggktech.dao.ExcelReader;
import com.ggktech.dao.PropertyFileReader;
import com.ggktech.service.DataProviders;
import com.ggktech.service.PublicLibrary;


public class NewTest
{
	public WebDriver driver;
	PropertyFileReader loadprop = new PropertyFileReader();
	DBConnection dbCon = new DBConnection();
	ExcelReader excel = new ExcelReader(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM);
	private Logger log = null;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void SetUp()
	{
		log = LogManager.getLogger(NewTest.class);
		String path = System.getProperty("user.dir");
		htmlReporter = new ExtentHtmlReporter(path+ File.separator + "ExtentReports\\extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		System.out.println(path); 
		WebDriverManager.chromedriver().setup();
		Map<String, Object> prefs=new HashMap<>();
		prefs.put("download.default_directory", path+ File.separator + "Downloads");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		log.info("Chrome window maximized");
	}

	@Test
	public void DataBasetest()
	{
		ArrayList<LinkedHashMap<String,String>> result = dbCon.GetRecordsListDictionary("Select * from AutomationForm");
		Iterator<LinkedHashMap<String,String>> itr = result.iterator();
		while(itr.hasNext())
		{	
		for(Map.Entry<String, String> entry:itr.next().entrySet())
		{
			System.out.println("Key value is: "+entry.getKey()+" and the value is: "+entry.getValue());
		}
		}
	}


	@Test
	public void Practice()
	{
		test = extent.createTest("Practice","Contains script related to automation form filling");
		driver.get(loadprop.LoadProperties(PropertyFileConstants.CONFIG_PROPERTIES, ConfigConstants.URL));
		PublicLibrary.click(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "CookieAccept_a"));
		test.pass("Closed cookie popup");
		PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "FirstName_input"), excel.getExcelValues(SheetConstants.FIRSTNAME, 1));
		test.pass("Entered first name in the form");
		PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "LastName_input"), excel.getExcelValues(SheetConstants.LASTNAME, 1));
		test.pass("Entered last name in the form");
		PublicLibrary.RadioButton(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "SexRadio_input"), excel.getExcelValues(SheetConstants.SEX, 1));
		test.pass("Selected an option in the Sex radio button");
		PublicLibrary.RadioButton(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "ExperienceRadio_input"), excel.getExcelValues(SheetConstants.EXPERIENCE, 1));
		test.pass("Selected an option in the Experience radio button");
		PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "DatePicker_input"), excel.getExcelValues(SheetConstants.DATE, 1));
		test.pass("Entered date value in the form");
		PublicLibrary.select(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "Continents_select"), excel.getExcelValues(SheetConstants.CONTINENT, 1));
		test.pass("Selected a value from the Continents dropdown");
	}

	@AfterMethod
	public void GetResult(ITestResult result)
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.fail("Test Case Failed is "+result.getName());
			test.fail("Test Case Failed is "+result.getThrowable());
			String screenshotPath;
			try 
			{
				screenshotPath = PublicLibrary.getScreenshot(driver, result.getName());
				test.addScreenCaptureFromPath(screenshotPath);
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}


	@Test
	public void Frames()
	{
		driver.get("http://toolsqa.com/iframe-practice-page/");

	}


	@Test(dataProvider="SampleTest", dataProviderClass = DataProviders.class, priority = 1)
	public void DataProviderExample(String username, String password)
	{

		System.out.println(username+" | "+password);
	}

	@Test(priority = -1)
	public void NegativePriority()
	{

		System.out.println("Negative priority");
	}


	@AfterTest
	public void TearDown()
	{
		extent.flush();
		driver.quit();
	}
}





