package Practice.Framework;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.ggktech.service.PublicLibrary;

public class ExtentReportExample 
{

	public static WebDriver driver;

	public static void main(String[] args) throws Exception
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		ExtentTest test = extent.createTest("SampleTest","description");
		
		String path = System.getProperty("user.dir");
		WebDriverManager.chromedriver().setup();
		Map<String, Object> prefs=new HashMap<>();
		prefs.put("download.default_directory", path+ File.separator + "Downloads");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		test.info("browser maximized");
		driver.get("https://www.toolsqa.com/Automation-practice-form/");
		test.pass("Navigated to automation practice form URL");
		PublicLibrary.click(driver, By.id("cookie_action_close_header"));
		test.pass("Closed cookie pop-up");
		PublicLibrary.sendKeys(driver, By.name("firstname"), "sravan");
		test.pass("Entered first name");
		PublicLibrary.sendKeys(driver, By.name("lastname"), "bhuma");
		test.fail("details", 
				MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		driver.quit();
		extent.flush();
	}

}
