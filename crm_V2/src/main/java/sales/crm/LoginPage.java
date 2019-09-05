package sales.crm;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	 WebDriver driver;
	 Logger log=null;
	 By userName = By.id("identifierId");
	 By password = By.cssSelector(".whsOnd.zHQkBf[type='password']");
	 By titleText =By.id("headingText");
	 By next = By.cssSelector(".RveJvd.snByac");
	//accessing the  page heading web element.
	By headerName=By.cssSelector("section h2.content-header");
	 
	 //Initializing the driver.
	 public LoginPage(WebDriver driver,Logger log)
	 {
	     this.driver = driver;
	     this.log=log;
	 }
	 
	 //Sending the username or emailid  its field.
	 public void sendUserName(String strUserName)
	 {
		 try
		 {			 
			 WrapperMethods.sendKeys(driver, userName, strUserName);
			 log.info("Username is successfully sent to its field");
		 }
		 catch(Exception e)
		 {
			 log.error("Username is not successfully send to its field");
		 }
	 }
	 
	 //Sending password to the password field.
	 public void sendPassword(String strPassword)
	 {
		 try
		 {			 
			//Thread.sleep(2000);
			WrapperMethods.sendKeys(driver, password, strPassword);
			log.info("Password is successfully sent to its field");
		 }
		 catch(Exception e)
		 {
			 log.error("Password is not successfully send to its field");
		 }
	 }
	 
	 //Clicking on the next button.
	 public void clickLoginNext()
	 {
		 try
		 {
			 WrapperMethods.click(driver, next);
			 log.info("Next button is sucessfully clicked");
		 }
		 catch(Exception e)
		 {
			 log.error("Next button is not clicked");
		 }
	 }
	 
}

