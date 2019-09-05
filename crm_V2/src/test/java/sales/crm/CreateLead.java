package sales.crm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateLead extends CRMLoginLogout{
  @Test
  public void creatingLead() {
	  CommonFieldsPage.moveTo(driver);
	  CreateLeadPage.clickLeadMang(driver);
	  CreateLeadPage.clickCreateLead(driver);
	  try
		{
			assertEquals(WrapperMethods.getText(driver, CreateLeadPage.headerName),"Lead Details");
			objReport.testCaseSteps("Navigate to create lead page", "User should successfully navigate to the create lead page",
					"User is successfully navigated to the create lead page",Status.PASS);
			log.info("User is successfully navigated to create lead page");
		}
		catch(AssertionError e)
		{
			objReport.testCaseSteps("Navigate to create lead page", "User should successfully navigate to the create lead page",
					"User is not successfully navigated to the create lead page",Status.FAIL);
			log.error("Unable to navigate to create lead page");
			driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
			assertTrue(false);
		}
	  
	  try
	  {
	  	
	  	CreateLeadPage.organisationName(driver, inputData.get("Organisation Name"));
	  	CreateLeadPage.webSiteName(driver, inputData.get("Website"));
	  	WrapperMethods.click(driver, By.id("Organisationmodel_NewsURL"));
	  	
	  	Boolean flag=isAlertPresent(driver);
	  	if(flag)
	  	{
	  		objReport.testCaseSteps("WebsiteName", "Website Name entered by user must be unique",
						"Already existing website name",Status.FAIL);
			assertTrue(false);
	  	}
	  	
	  	WrapperMethods.sendKeys(driver,By.id("Organisationmodel_NewsURL") , "newURL");
	  	
	  	CreateLeadPage.selectCountry(driver, inputData.get("Country"));
	  	CreateLeadPage.selectState(driver, inputData.get("State"));
	  	
		CreateLeadPage.firstName(driver, inputData.get("FirstName"));
		CreateLeadPage.lastName(driver, inputData.get("LastName"));
		CreateLeadPage.designation(driver, inputData.get("Designation"));
		CreateLeadPage.email(driver, inputData.get("Email"));
		CreateLeadPage.selectContactStatus(driver, inputData.get("ContactStatus"));
	  	
		CreateLeadPage.clickAddButton(driver);
	  	CreateLeadPage.clickSaveButton(driver);
	  	 
	  	assertEquals(WrapperMethods.getText(driver, CreateLeadPage.successMsg), "Saved Successfully!!!");
	  	objReport.testCaseSteps("Filling form", "Fill all mandatory fields and clicking on save button",
				"Successfully filled all mandatory fields and clicking on save button",Status.PASS);
	  	log.info("Successfully filled all the mandatory fields and Clicked on save button in create lead page");
	  }
	  catch(AssertionError ex)
	  {
		 log.info("Unable to fill all the mandatory fields in create lead page");
		 objReport.testCaseSteps("Filling form", "Fill all mandatory fields and clicking on save button",
					"Unable to fill all mandatory fields and clicking on save button",Status.FAIL);
		 driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
		 assertTrue(false);
	  }
	  catch(Exception ex)
	  {
		  log.info("Unable to fill all the mandatory fields in create lead page");
		  objReport.testCaseSteps("Filling form", "Fill all mandatory fields and clicking on save button",
					"Unable to fill all mandatory fields and clicking on save button",Status.FAIL);
		  driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
		  assertTrue(false);
	  }
	  	
	  //Verifying the Creation of lead
	 
	  try
		{
		    CommonFieldsPage.moveTo(driver);
		    SearchLeadPage.clickSearchLead(driver);
			assertEquals(WrapperMethods.getText(driver, CreateLeadPage.headerName),"Search Lead Details");
			objReport.testCaseSteps( "Navigate to create Search lead page", "User should successfully navigate to the search lead page",
					"User is successfully navigated to the search lead page",Status.PASS);
			log.info("User is successfully navigated to search lead page");
			SearchLeadPage.clickToSelectType(driver, "Is");
		  	SearchLeadPage.sendToWebsiteSearch(driver, inputData.get("Website"));
		}
		catch(AssertionError e)
		{
			CheckResult.takeSnapShot(driver, "screenShotCreateLead.png");
			objReport.testCaseSteps("Navigate to search lead page", "User should successfully navigate to the search lead page",
					"User is not successfully navigated to the search lead page",Status.FAIL);
			log.error("Unable to navigate to search lead page");
			driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
			assertTrue(false);
		}
	  	catch(Exception ex)
	  	{
	  		CheckResult.takeSnapShot(driver, "screenShotCreateLead.png");
			objReport.testCaseSteps("Navigate to search lead page", "User should successfully navigate to the search lead page",
					"User is not successfully navigated to the search lead page",Status.FAIL);
			log.error("Unable to navigate to search lead page");
			driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
			assertTrue(false);
	  	}
	  
	    //Delay.delay(2000);
	  	WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOf(SearchLeadPage.tableLoading(driver)));
	  	
	  	List<WebElement> list = null;
	  	try
	  	{
	  		list=driver.findElements(SearchLeadPage.gridDataLocator);
	  		objReport.testCaseSteps("Retrieving data from the grid in Search Lead page", "Data retrieval should be successful",
					"Data is successfully retrived from grid",Status.PASS);
			log.info("Data is successfully retrived from grid");
	  	}
	  	catch(Exception ex)
	  	{
	  		objReport.testCaseSteps("Retrieving data from the grid in Search Lead page", "Data retrieval should be successful",
					"Data is not successfully retrived from grid",Status.FAIL);
			log.error("Data is not successfully retrived from grid");
			driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
			assertTrue(false);
	  	}
	  	
	  	if(list.size()==1)
	  	{
	  		objReport.testCaseSteps( "Verification of Create lead", "Lead should be created successfully",
					"Lead is not created",Status.FAIL);
			log.error("Lead is not created");
			driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
			assertTrue(false);
	  	}
	  	else
	  	{
	  		try
	  		{
	  			assertEquals(WrapperMethods.getText(driver, list.get(2)), inputData.get("Website"));
	  			objReport.testCaseSteps( "Verification of Create lead", "Lead should be created successfully",
					"Lead is created successfully",Status.PASS);
	  			log.info("Lead is created successfully");
	  		}
	  		catch(Exception ex)
	  		{
	  			objReport.testCaseSteps( "Verification of Create lead", "Lead should be created successfully",
						"Lead is not created",Status.FAIL);
				log.error("Lead is not created");
				driver=CloseAction.closingActions(driver, "screenShotCreateLead.png");
				assertTrue(false);
	  		}	
	  	}
  	}

private Boolean isAlertPresent(WebDriver driver) {
	Boolean flag=false;
	Delay.delay(1000);
	try{
		WebDriverWait wait=new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
  		Alert alert=driver.switchTo().alert();
  		alert.accept();
  		flag=true;
	}
	catch(Exception ex)
	{
		flag=false;
	}
	return flag;
}
}
