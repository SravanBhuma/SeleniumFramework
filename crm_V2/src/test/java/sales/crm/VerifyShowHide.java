package sales.crm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class VerifyShowHide extends CRMLoginLogout{
  @Test
  public void verifyingShowHide() {
	  
	  try
		{
		    CommonFieldsPage.moveTo(driver);
		    SearchLeadPage.clickLeadMang(driver);
		    SearchLeadPage.clickSearchLead(driver);
			assertEquals(WrapperMethods.getText(driver, CreateLeadPage.headerName),"Search Lead Details");
			objReport.testCaseSteps("Navigate to search lead page", "User should successfully navigate to the search lead page",
					"User is successfully navigated to the search lead page",Status.PASS);
			log.info("User is successfully navigated to search lead page");
		}
		catch(AssertionError e)
		{
			objReport.testCaseSteps("Navigate to search lead page", "User should successfully navigate to the search lead page",
					"User is not successfully navigated to the search lead page or Header name is not correct",Status.FAIL);
			log.error("Unable to navigate to search lead page");
			driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
			assertTrue(false);
		}
	  
	  
	  
	  try
	  {
		  WrapperMethods.click(driver, SearchLeadPage.showHidelocator);
		  assertTrue(WrapperMethods.isDisplayed(driver, SearchLeadPage.headerDropdown));
		  objReport.testCaseSteps("Clicking on 'Show/Hide columns' field", "A drop down containing all the header names should be displayed",
					"Drop down containing all the header names is displayed",Status.PASS);
		  log.info("Drop down containing all the header names is displayed");  
	  }
	  catch(AssertionError e)
	  {
		  objReport.testCaseSteps("Clicking on Show/Hide columns", "A drop down containing all the header names should be displayed",
					"Drop down containing all the header names is not displayed",Status.FAIL);
		  log.info("Drop down containing all the header names is not displayed");  
		  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
		  assertTrue(false);
	  }
	  List<WebElement> headerNamesInDropdown = null;
	  List<String> headerNames = null;
	  List<WebElement> headerNamesInGrid;
	  List<String> tableHeaders;
	  try
	  {
		  WrapperMethods.click(driver, SearchLeadPage.selectAllHeaders);
	  
		  headerNamesInDropdown=driver.findElements(SearchLeadPage.allheaderNames);
		  headerNames = new LinkedList<String>();
		  for(WebElement element:headerNamesInDropdown)
		  {
			  headerNames.add(WrapperMethods.getText(driver, element).trim());
		  }
	  
	  }
	  catch(Exception ex)
	  {
		  objReport.testCaseSteps("Clicking on select all option", "Select All option should be clicked",
				  "Select All is not clicked",Status.FAIL);
		  log.error("Select All is not clicked");
		  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
		  assertTrue(false);
	  }
	  
	  try
	  {
		  for(WebElement element:headerNamesInDropdown)
		  {
			  WrapperMethods.click(driver, element);
			  String headerName=WrapperMethods.getText(driver, element).trim();
			  log.info("Clicking on "+headerName+" to hide its column in grid");
			  headerNamesInGrid=driver.findElements(SearchLeadPage.allColumns);
			  tableHeaders = new LinkedList<String>();
			  for(WebElement hName:headerNamesInGrid)
			  {
				  tableHeaders.add(WrapperMethods.getText(driver, hName).trim());
			  }
			  if(!headerName.equalsIgnoreCase("Select"))
			  {
				  if(!tableHeaders.contains(headerName))
				  {
					  objReport.testCaseSteps("Verifying the Hide functionality of '"+headerName+"' header", "Hide functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should disappear",
						  "Hide functionality of '"+headerName+"' header is correct, '"+headerName+"' column is disappeared",Status.PASS);
					  log.info("Hide functionality of '"+headerName+"' header is correct, '"+headerName+"' column is disappeared");
				  }
				  else if(tableHeaders.contains(headerName))
				  {
					  objReport.testCaseSteps("Verifying the Hide functionality of '"+headerName+"' header", "Hide functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should disappear",
						  "Hide functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not disappeared",Status.FAIL);
					  log.error("Hide functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not disappeared");
					  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
					  assertTrue(false);
				  }
			  }
			  else if(headerName.equalsIgnoreCase("Select"))
			  {
				  if(!tableHeaders.contains(""))
				  {
					  objReport.testCaseSteps("Verifying the Hide functionality of '"+headerName+"' header", "Hide functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should disappear",
						  "Hide functionality of '"+headerName+"' header is correct, '"+headerName+"' column is disappeared",Status.PASS);
					  log.info("Hide functionality of '"+headerName+"' header is correct, '"+headerName+"' column is disappeared");		  
				  }
				  else if(tableHeaders.contains(""))
				  {
					  objReport.testCaseSteps("Verifying the Hide functionality of '"+headerName+"' header", "Hide functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should disappear",
						  "Hide functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not disappeared",Status.FAIL);
					  log.error("Hide functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not disappeared");
					  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
					  assertTrue(false);
				  }
			  }
		  }
	  }
	  catch(Exception ex)
	  {
		  objReport.testCaseSteps("Verifying the Hide functionality", "Hide functionality of  header should be correct",
				  "Unable to verify Hide functionality",Status.FAIL);
		  log.error("Unable to verify Hide functionality");
		  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
		  assertTrue(false);
	  }
	  
	  try
	  {
		  for(WebElement element:headerNamesInDropdown)
		  {
			  WrapperMethods.click(driver, element);
			  String headerName=WrapperMethods.getText(driver, element).trim();
			  log.info("Clicking on "+headerName+" to show its column in grid");
			  headerNamesInGrid=driver.findElements(SearchLeadPage.allColumns);
			  tableHeaders = new LinkedList<String>();
			  for(WebElement hName:headerNamesInGrid)
			  {
				  tableHeaders.add(WrapperMethods.getText(driver, hName).trim());
			  }
			  if(!headerName.equalsIgnoreCase("Select"))
			  {
				  if(tableHeaders.contains(headerName))
				  {
					  objReport.testCaseSteps("Verifying the Show functionality of '"+headerName+"' header", "Show functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should be displayed",
							  "Show functionality of '"+headerName+"' header is correct, '"+headerName+"' column is displayed",Status.PASS);
						  log.info("Show functionality of '"+headerName+"' header is correct, '"+headerName+"' column is displayed");
				  }
				  else if(!tableHeaders.contains(headerName))
				  {
					  objReport.testCaseSteps("Verifying the Show functionality of '"+headerName+"' header", "Show functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should be displayed",
							  "Show functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not displayed",Status.FAIL);
						  log.error("Show functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not displayed");
						  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
						  assertTrue(false);
				  }
			  }
			  else if(headerName.equalsIgnoreCase("Select"))
			  {
				  if(tableHeaders.contains(""))
				  {
					  objReport.testCaseSteps("Verifying the Show functionality of '"+headerName+"' header", "Show functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should be displayed",
							  "Show functionality of '"+headerName+"' header is correct, '"+headerName+"' column is displayed",Status.PASS);
						  log.info("Show functionality of '"+headerName+"' header is correct, '"+headerName+"' column is displayed");  
				  }
				  else if(!tableHeaders.contains(""))
				  {
					  objReport.testCaseSteps("Verifying the Show functionality of '"+headerName+"' header", "Show functionality of '"+headerName+"' header should be correct, '"+headerName+"' column should be displayed",
							  "Show functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not displayed",Status.FAIL);
						  log.error("Show functionality of '"+headerName+"' header is not correct, '"+headerName+"' column is not displayed");
						  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
						  assertTrue(false);
				  }
			  }
		  }
	  }
	  catch(Exception ex)
	  {
		  objReport.testCaseSteps("Verifying the Show functionality", "Show functionality of  header should be correct",
				  "Unable to verify Show functionality",Status.FAIL);
		  log.error("Unable to verify Show functionality");
		  driver=CloseAction.closingActions(driver, "screenShotShowHide.png");
		  assertTrue(false);
	  }
  }
}
