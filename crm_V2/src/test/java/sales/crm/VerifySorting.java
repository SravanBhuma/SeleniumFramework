package sales.crm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class VerifySorting extends CRMLoginLogout{
	static int index=3;
  @Test
  public void verifyingSorting() {
	  String headerName=null;
	 
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
	  
	  
	  float tableSize=Float.parseFloat(inputData.get("GridSize"));
	  int sizeOfTable=(int) (tableSize);
	  try
	  {
		  SearchLeadPage.setgridSize(driver,""+sizeOfTable);
		  objReport.testCaseSteps("Selecting an option for resizing the grid", "User should successfully select an option",
					"User is successfully selected an option for resizing",Status.PASS);
		  log.info("User is successfully selected an option for resizing the grid");
	  }
	  catch(Exception ex)
	  {
		  objReport.testCaseSteps("Selecting an option for resizing the grid", "User should successfully select an option",
					"User is not able to select an option for resizing",Status.PASS);
		  log.info("User is not able to select an option for resizing the grid");
	  }
	  
	 
	  Delay.delay(3000);
	  
	  List<WebElement> headerList=SearchLeadPage.headerList(driver);
	  for(int i=0;i<headerList.size();i++)
	  {
		  SearchLeadPage.clickGridHeader(driver, headerList.get(i));
		  headerName=WrapperMethods.getText(driver,headerList.get(i));
		 
		  Delay.delay(1000);
	  
		  List<WebElement> list=driver.findElements(SearchLeadPage.gridDataLoc(driver, i+2));
		  List<String> gridValues=new LinkedList<String>();
		  for (WebElement li : list) 
		  {
			  gridValues.add(WrapperMethods.getText(driver, li));
		  }

		  
		  if(list.size()>0)
			  verifyGrid(driver, gridValues,"Asc",headerName);
		  
		 
		  Delay.delay(1000);
		  SearchLeadPage.clickGridHeader(driver, headerList.get(i));
		  Delay.delay(1000);
		  list=driver.findElements(SearchLeadPage.gridDataLoc(driver, i+2));
		  gridValues=new LinkedList<String>();
		  for (WebElement li : list) 
		  {
			  gridValues.add(WrapperMethods.getText(driver, li));
		  }

		  if(list.size()>0)
			  verifyGrid(driver,gridValues,"Desc",headerName);
		  
		  Delay.delay(1000);
		  
	  }
	  
  }
  public static void verifyGrid(WebDriver driver, List<String> gridValues,String type,String headerName)
  {
	  Report objReport=new Report();
	  int i=0;
	  String sortType = null;
	  List<Date> dateValues=new LinkedList<Date>();
	  if(headerName.equalsIgnoreCase("Modified Date"))
	  {
		  try 
		  {
			  for(String value:gridValues)
			  {
				  String dateValue=value;
				  DateFormat formatter ; 
				  Date date ; 
				  formatter = new SimpleDateFormat("M/d/yyyy");
				  date = formatter.parse(dateValue);
				  dateValues.add(date);
			  }
		  }
		  catch (ParseException e) 
		  {
				e.printStackTrace();
		  }
		  int temp;
		  boolean flag=true;
		  for(i=0;i<dateValues.size()-1;i++)
		  {
		  		 temp=dateValues.get(i).compareTo(dateValues.get(i+1));
				  if(type.equalsIgnoreCase("Asc"))
				  {
					  if(temp<=0)
					  {
						  sortType="Asc";
					  }
					  else if(temp>0)
					  {
						  sortType="Desc";
					  }
				  }
				  else
				  {
					  if(temp<0)
				      {
						  sortType="Asc";
				      }
					  else if(temp>=0)
				      {
						  sortType="Desc";
				      }
				  }
				  if(temp==0 || sortType.equalsIgnoreCase(type))
				  {
					 // System.out.println("i:"+gridValues.get(i).toString()+"  i+1:"+gridValues.get(i+1).toString()+"  temp:"+temp);
					  flag=true;
				  }
				  else
				  {
					  //System.out.println("i:"+gridValues.get(i).toString()+"  i+1:"+gridValues.get(i+1).toString()+"  temp:"+temp);
					  flag=false;
					  break;
				  }
		  	}

		  	if(flag)
		  	{
					objReport.testCaseSteps( "Verifying '"+type+"' order of '"+headerName+"' Column", "All the elements of '"+headerName+"' should be in '"+type+"' order",
							"'"+headerName+"' Column is according to '"+type+"' order",Status.PASS);
					log.info(headerName+" Column is according to '"+type+"' order");
		  	}
		  	else
		  	{
			  	CheckResult.takeSnapShot(driver, "screenShotVerifySorting.png");
			  	objReport.testCaseSteps( "Verifying '"+type+"' order of '"+headerName+"' Column", "All the elements of '"+headerName+"' should be in '"+type+"' order",
						"'"+headerName+"' Column is not according to '"+type+"' order",Status.FAIL);
				log.error(headerName+" Column is not according to '"+type+"' order");
				driver=CloseAction.closingActions(driver, "screenShotVerifySorting.png");
				assertTrue(false);
		  	}
		  
	  }
	  else if(!headerName.equalsIgnoreCase("Organisation Name") && !headerName.equalsIgnoreCase("Location/City"))
	  {
	  	int temp;
	  	boolean flag=true;
	  	for(i=0;i<gridValues.size()-1;i++)
	  	{
		  temp=gridValues.get(i).toString().compareToIgnoreCase(gridValues.get(i+1).toString());
		  if(type.equalsIgnoreCase("Asc"))
		  {
			  if(temp<=0)
			  {
				  sortType="Asc";
			  }
			  else if(temp>0)
			  {
				  sortType="Desc";
			  }
		  }
		  else
		  {
			  if(temp<0)
		      {
				  sortType="Asc";
		      }
			  else if(temp>=0)
		      {
				  sortType="Desc";
		      }
		  }
		  if(temp==0 || sortType.equalsIgnoreCase(type))
		  {
			  //System.out.println("i:"+gridValues.get(i).toString()+"  i+1:"+gridValues.get(i+1).toString()+"  temp:"+temp);
			  flag=true;
		  }
		  else
		  {
			  //System.out.println("i:"+gridValues.get(i).toString()+"  i+1:"+gridValues.get(i+1).toString()+"  temp:"+temp);
			  flag=false;
			  break;
		  }
	  	}
	  	if(flag)
	  	{
		  	objReport.testCaseSteps( "Verifying '"+type+"' order of '"+headerName+"' Column", "All the elements of '"+headerName+"' should be in '"+type+"' order",
					"'"+headerName+"' Column is according to '"+type+"' order",Status.PASS);
			log.info(headerName+" Column is according to '"+type+"' order");
	  	}
	  	else
	  	{
		  	CheckResult.takeSnapShot(driver, "screenShotVerifySorting.png");
		  	objReport.testCaseSteps( "Verifying '"+type+"' order of '"+headerName+"' Column", "All the elements of '"+headerName+"' should be in '"+type+"' order",
					"'"+headerName+"' Column is not according to '"+type+"' order",Status.FAIL);
			log.error(headerName+" Column is not according to '"+type+"' order");
			driver=CloseAction.closingActions(driver, "screenShotVerifySorting.png");
			assertTrue(false);
	  	}
	  }
  }
}

