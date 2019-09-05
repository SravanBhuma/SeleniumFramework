package sales.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateLeadPage {
	
	static JavascriptExecutor jsExec;
	static WebElement element;
	public static By leadMang=By.xpath("//a/span[contains(.,'Lead Management')]");
	public static By createLead=By.xpath("//a/span[contains(.,'Create Lead')]");
	public static By headerName=By.cssSelector("section h2.content-header");
	public static By orgName=By.id("OrganizationName");
	public static By website=By.id("website");
	public static By country=By.id("CountryID");
	public static By state=By.id("StateID");
	public static By firstName=By.id("FirstName");
	public static By lastName=By.id("LastName");
	public static By designation=By.id("Designation");
	public static By email=By.id("EmailAddress");
	public static By contactStatus=By.id("ContactStatusID");
	public static By addContactBtn=By.id("add-update-btn");
	public static By saveBtn=By.id("btnSaveLead");//btnCancel
	public static By successMsg=By.xpath("//div[@id='leadSaveStatus']/label");
	
	public static WebElement selectCountryValue(WebDriver driver,String data)
	{
		return driver.findElement(By.xpath("//ul[@id='ui-id-25']/li[text()='"+data+"']"));
	}
	
	public static WebElement selectStateValue(WebDriver driver,String data)
	{
		return driver.findElement(By.xpath("//ul[@id='ui-id-26']/li[text()='"+data+"']"));
	}
	
	public static void clickLeadMang(WebDriver driver)
	{
		 WrapperMethods.click(driver, leadMang);
	}
	
	public static void clickCreateLead(WebDriver driver)
	{
		 WrapperMethods.click(driver, createLead);
	}
	
	public static void organisationName(WebDriver driver,String data)
	{
		WrapperMethods.sendKeys(driver, orgName, data);
	}
	
	public static void webSiteName(WebDriver driver,String data)
	{
	  	WrapperMethods.sendKeys(driver, website, data);
	}
	
	public static void firstName(WebDriver driver,String data)
	{
		WrapperMethods.sendKeys(driver, firstName, data );
	}
	
	public static void lastName(WebDriver driver,String data)
	{
		WrapperMethods.sendKeys(driver, lastName, data);
	}
	
	public static void designation(WebDriver driver,String data)
	{
		WrapperMethods.sendKeys(driver, designation, data );  
	}
	
	public static void email(WebDriver driver,String data)
	{
		WrapperMethods.sendKeys(driver, email, data);
	}
	
	public static void selectCountry(WebDriver driver, String data)
	{
		WrapperMethods.click(driver, country);
	  	element=CreateLeadPage.selectCountryValue(driver, data);
	  	
	  	jsExec = ((JavascriptExecutor) driver);
	  	do{
	  	jsExec.executeScript("scroll(0,250)");
	  	}while(!element.isDisplayed());
	  	WrapperMethods.click(driver, element);
	}

	public static void selectState(WebDriver driver, String data) {
		WrapperMethods.click(driver, state);
	  	element=CreateLeadPage.selectStateValue(driver, data);
	  	
	  	jsExec = ((JavascriptExecutor) driver);
	  	do{
		  	jsExec.executeScript("scroll(0,250)");
		}while(!element.isDisplayed());
		WrapperMethods.click(driver, element);
		
	}

	public static void selectContactStatus(WebDriver driver, String data) 
	{
		WrapperMethods.select(driver, contactStatus, data);
	}

	public static void clickAddButton(WebDriver driver) {
		WrapperMethods.click(driver, addContactBtn);
	}
	
	public static void clickSaveButton(WebDriver driver) {
		WrapperMethods.click(driver, CreateLeadPage.saveBtn);
	}
}
