package Practice.Framework;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGMethodsTestThree 
{
	 @Test(groups ={"sanity", "regression"})
	  public void TestMethodG()
	  {
		  System.out.println("Executed Test method - TestMethodG");
	  }
	  
	  @Test(groups ={"sanity"})
	  @Parameters({"NameParameter"})
	  public void TestMethodH(@Optional("OptionalParameterCheck")String name)
	  {
		  System.out.println("Executed Test method - TestMethodH : "+name);
	  }
}
