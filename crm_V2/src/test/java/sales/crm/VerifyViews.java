package sales.crm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class VerifyViews extends CRMLoginLogout{
  @Test
  public void verifyingViews(){
	  try
	  {
		    CommonFieldsPage.moveTo(driver);
		    SearchLeadPage.clickLeadMang(driver);
		    SearchLeadPage.clickSearchLead(driver);
			assertEquals(WrapperMethods.getText(driver, CreateLeadPage.headerName),"Search Lead Details");
			objReport.testCaseSteps( "Navigate to  Search lead page", "User should successfully navigate to the search lead page",
					"User is successfully navigated to the search lead page",Status.PASS);
			log.info("User is successfully navigated to search lead page");
	  }
	  catch(Exception e)
	  {
			CheckResult.takeSnapShot(driver, "screenShotVerifySorting.png");
			objReport.testCaseSteps("Navigate to search lead page", "User should successfully navigate to the search lead page",
					"User is not successfully navigated to the search lead page",Status.FAIL);
			log.error("Unable to navigate to search lead page");
			driver=CloseAction.closingActions(driver, "screenShotVerifySorting.png");
			assertTrue(false);
	  }
	  		
	  
  }
}
