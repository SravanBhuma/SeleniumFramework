package sales.crm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class CRMLoginLogout {
	public enum Status {
		PASS,FAIL
	}
	WebDriver driver;
	DesiredCapabilities capabilities;
	LoginPage objLogin;
	Properties property;
	static Logger log=null;
	static int totalTestCases=0;
	static int passed=0;
	static int failed=0;
	int stepNo=1;
	Report objReport;
	ExcelData objData;
	HashMap<String,String> inputData;
	@BeforeSuite
	public void driverSetup()
	{
		property = new Properties();
		try {
			FileInputStream objFile = new FileInputStream("config.properties");
			property.load(objFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(property.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + File.separator+ "lib/chromedriver.exe");
		}
		else if(property.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + File.separator+ "lib/geckodriver.exe");
		}
		else
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + File.separator+ "lib/IEDriverServer.exe");
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		}
	    objReport=new Report();
	    objReport.createFile(property.getProperty("browser"));	      
	}
	@BeforeClass
	public void setup() throws InterruptedException
	{
		objReport=new Report();
		objData=new ExcelData();
		objReport.testCaseStepsHeader(this.getClass().getSimpleName());
		property = new Properties();
		try {
			FileInputStream objFile = new FileInputStream("config.properties");
			property.load(objFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(property.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(property.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new InternetExplorerDriver(capabilities);
		}
		
	    driver.get(property.getProperty("url"));
	    if(!property.getProperty("browser").equalsIgnoreCase("firefox"))
	    driver.manage().window().maximize();
	    
	    log = Logger.getLogger("salesCRM");
	    String username = null;
	    String password = null;
		
	    //Getting the username and password from the excel sheet by sending the test case name and the parameter name.
		log.info("Retrieving Username from excel");
		inputData=objData.getData(this.getClass().getSimpleName());
		username=inputData.get("Username");
		password=inputData.get("Password");
		log.info("Retrieving Password from excel");
		objLogin = new LoginPage(driver,log);
		
		//sending the username and password to their respective fields and clicking on next button.
		log.info("Sending username to its field");
		objLogin.sendUserName(username);
		log.info("Clicking on next button after sending username");
		objLogin.clickLoginNext();
		log.info("Sending password to its field");
		objLogin.sendPassword(password);
		Delay.delay(1000);
		log.info("Clicking on next button after sending password");
		objLogin.clickLoginNext();
		try
		{
			assertEquals(WrapperMethods.getText(driver, objLogin.headerName),"Dashboards");
			objReport.testCaseSteps("Navigate to site and login", "Site should be open and Login should be success",
					"Site is opened and Login is success",Status.PASS);
			log.info("User is successfully navigated to site");
		}
		catch(Exception e)
		{
			objReport.testCaseSteps("Navigate to site and login", "Site should be open and Login should be success"
					,"Site is opened and Login is failed",Status.FAIL);
			log.error("Login failed");
			driver=CloseAction.closingActions(driver, "screenShotLogin.png");
			assertTrue(false);
		}
	}

	 @AfterMethod
	  public void result(ITestResult result)
	  {
		  new CheckResult().verifyResult(result);
	  }
	  @AfterClass
	  public void logout()
	  {		
		objReport=new Report();
		if(driver!=null)  
		{
		  //logout from the application.
		 try
		 {
			 Delay.delay(1000);
			 try
			 {
				 log.info("Clicking on profile drop down toggle");
				 WrapperMethods.click(driver, CommonFieldsPage.dropdown_toggle);
				 log.info("Profile drop down toggle is successfully clicked");
			 }
			 catch(Exception e)
			 {
				 log.error("Profile drop down toggle is not clicked");
			 }
			 try
			 {
				 log.info("Clicking on log out button");
				 WrapperMethods.click(driver, CommonFieldsPage.logout);
				 assertEquals(driver.findElement(CommonFieldsPage.headerAfterLogout).getText(),"Choose an account");
				 log.info("Log out button is successfully clicked, logout is successful");
				 objReport.testCaseSteps("Logout from the Application", "User should logout from Application"
							,"User successfully logged out of application",Status.PASS);
			 }
			 catch(Exception e)
			 {
				 log.error("Log out button is not clicked");
				 objReport.testCaseSteps("Logout from the Application", "User should logout from Application"
							,"User is not able to log out from application",Status.FAIL);
				 driver=CloseAction.closingActions(driver, "screenShotLogout.png");
				 assertTrue(false);
			 }
			 driver.close();
			
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
			 System.out.println(ex.getMessage());
			 driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
			 assertTrue(false);
		 }
		}
		else
		{
		}
	  }
	 @AfterSuite
	 public void testCaseResult()
	 {
		 Report objReport=new Report();
		 objReport.testCaseMetrics(totalTestCases,passed,failed);
	 }
}

