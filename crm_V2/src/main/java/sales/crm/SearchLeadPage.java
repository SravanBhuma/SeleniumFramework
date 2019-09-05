package sales.crm;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

public class SearchLeadPage {
	public static By leadMang=By.xpath("//a/span[contains(.,'Lead Management')]");
	public static By searchLead=By.xpath("//a/span[contains(.,'Search Leads')]");
	public static By websiteSearchBox=By.id("OrganisationURL");
	public static By gridDataLocator=By.xpath("//table[@id='table_Lead']/tbody/tr/td");
	public static By select=By.cssSelector("table#table_Lead thead tr th:nth-child(3) select");
	public static By gridHeader=By.cssSelector("div#gridscroll thead tr:nth-child(1) th:nth-child(2)");
	public static By gridData=By.cssSelector("div#gridscroll tbody tr td:nth-child(2)");
	public static By resize=By.cssSelector(".form-control.input-sm");
	public static By showHidelocator=By.id("opener");
	public static By headerDropdown=By.xpath("//div[@id='opener']/following-sibling::ul");
	public static By allheaderNames=By.xpath("//div[@id='opener']/following-sibling::ul/li/a[@class='toggle-vis-child tog_active']");
	public static By selectAllHeaders=By.xpath("//div[@id='opener']/following-sibling::ul/li/a[@class='toggle-vis-child']");
	public static By allColumns=By.xpath("//table[@id='table_Lead']/thead/tr/th[@class='sorting' or @class='sorting_desc' or @class='sorting_disabled']");
	
	public static void clickLeadMang(WebDriver driver)
	{
		 WrapperMethods.click(driver, leadMang);
	}
	public static void clickSearchLead(WebDriver driver)
	{
		WrapperMethods.click(driver, searchLead);
	}
	public static void sendToWebsiteSearch(WebDriver driver,String data)
	{
		WrapperMethods.sendKeys(driver, websiteSearchBox, data);
		WrapperMethods.enter(driver, SearchLeadPage.websiteSearchBox);
	}
	
	public static void clickToSelectType(WebDriver driver, String data)
	{
		WrapperMethods.click(driver, select);
		WrapperMethods.select(driver, select, data);
	}
	
	/*public static void clickGridHeader(WebDriver driver,int index)
	{
		WrapperMethods.click(driver, gridHeaderLocator(index));
	}
	
	public static void clickGridHeaderDesc(WebDriver driver,int index)
	{
		WrapperMethods.click(driver, gridHeaderLocatorDesc(index));
	}*/
	
	public static void clickGridHeader(WebDriver driver,WebElement element)
	{
		WrapperMethods.click(driver, element);
	}
	
	public static void setgridSize(WebDriver driver,String data)
	{
		WrapperMethods.select(driver, resize, data);
	}
	
	/*public static By gridHeaderLocator(int index)
	{
		return By.cssSelector("div#gridscroll thead tr:nth-child(1) th:nth-child("+index+").sorting");
	}
	
	public static By gridHeaderLocatorDesc(int index)
	{
		return By.cssSelector("div#gridscroll thead tr:nth-child(1) th:nth-child("+index+").sorting_asc");
	}*/
	
	public static By gridDataLoc(WebDriver driver,int index)
	{
		return By.cssSelector("div#gridscroll tbody tr td:nth-child("+index+")");
	}
	
	public static List<WebElement> headerList(WebDriver driver)
	{
		return driver.findElements(By.xpath("//table[@id='table_Lead']/thead/tr/th[@class='sorting' or @class='sorting_desc' or @class='sorting_asc']"));
	}
	
	public static WebElement tableLoading(WebDriver driver)
	{
		return driver.findElement(By.id("table_Lead_processing"));
	}
	
}
