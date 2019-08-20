package Practice.Framework;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.utils.ConfigConstants;
import com.framework.utils.PropertyFileConstants;
import com.framework.utils.SheetConstants;
import com.ggktech.dao.ExcelReader;
import com.ggktech.dao.PropertyFileReader;
import com.ggktech.service.*;


public class NewTest
{
	public WebDriver driver;
	PropertyFileReader loadprop = new PropertyFileReader();
	ExcelReader excel = new ExcelReader(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM);
	private Logger log = null;
	
	@BeforeTest
	public void SetUp()
	{
		log = LogManager.getLogger(NewTest.class);
		String path = System.getProperty("user.dir");
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
	public void Practice()
	{
		driver.get(loadprop.LoadProperties(PropertyFileConstants.CONFIG_PROPERTIES, ConfigConstants.URL));
		PublicLibrary.click(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "CookieAccept_a"));
		PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "FirstName_input"), excel.getExcelValues(SheetConstants.FIRSTNAME, 1));
		PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "LastName_input"), excel.getExcelValues(SheetConstants.LASTNAME, 1));
		PublicLibrary.click(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "MaleRadio_Input"));
		driver.findElement(By.xpath("//label[text()='Years of Experience']//ancestor::div[1]//input[@type='radio' and @value="+excel.getExcelValues(SheetConstants.EXPERIENCE, 1)+"]")).click();
		PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "DatePicker_input"), excel.getExcelValues(SheetConstants.DATE, 1));
		WebElement ContinentsDropdown = driver.findElement(loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "Continents_select"));
		Select options = new Select(ContinentsDropdown);
		options.selectByVisibleText(excel.getExcelValues(SheetConstants.CONTINENT, 1));
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
		driver.quit();
	}
}






//@Test
//public void Practice()
//{
//	driver.get(loadprop.LoadProperties(PropertyFileConstants.CONFIG_PROPERTIES, ConfigConstants.URL));
//	PublicLibrary.click(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "CookieAccept_a"));
//	PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "FirstName_input"), excel.getExcelValues(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM, SheetConstants.FIRSTNAME, 1));
//	PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "LastName_input"), excel.getExcelValues(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM, SheetConstants.LASTNAME, 1));
//	PublicLibrary.click(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "MaleRadio_Input"));
//	driver.findElement(By.xpath("//label[text()='Years of Experience']//ancestor::div[1]//input[@type='radio' and @value="+excel.getExcelValues(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM, SheetConstants.EXPERIENCE, 1)+"]")).click();
//	PublicLibrary.sendKeys(driver, loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "DatePicker_input"), excel.getExcelValues(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM, SheetConstants.DATE, 1));
//	WebElement ContinentsDropdown = driver.findElement(loadprop.byLocator(PropertyFileConstants.OR_PROPERTIES, "Continents_select"));
//	Select options = new Select(ContinentsDropdown);
//	options.selectByVisibleText(excel.getExcelValues(SheetConstants.TESTDATA,SheetConstants.AUTOMATIONFORM, SheetConstants.CONTINENT, 1));
//}


//System.setProperty("webdriver.chrome.driver",path+ File.separator+ PathConstants.RESOURCE_PATH +File.separator+"chromedriver.exe");
