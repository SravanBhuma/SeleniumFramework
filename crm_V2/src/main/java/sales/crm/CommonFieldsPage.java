package sales.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonFieldsPage {
	public static By dropdown_toggle=By.xpath("//a[@class='dropdown-toggle']/span[contains(.,'Test Admin')]");
	public static By logout=By.id("btnLogout");
	public static By headerAfterLogout=By.id("headingText");	
	public static By sideBar=By.xpath("//aside[@class='main-sidebar']/section[@class='sidebar']");
	public static By loading=By.id("loading");
	//accessing the  page heading web element.
	public static By headerName=By.className("content-header");	
	//Initializing the web driver.
	
	public static void moveTo(WebDriver driver)
	{
		 WrapperMethods.moveToElement(driver, CommonFieldsPage.sideBar);
	}
	
}
