package Practice.Framework;

import org.testng.annotations.Test;

public class TestNGMethodsTestTwo 
{
	  @Test(groups={"smoke"})
	  public void TestMethodC()
	  {
		  System.out.println("Executed Test method - TestMethodC");
	  }
	  
	  @Test(groups={"sanity", "regression"})
	  public void TestMethodD()
	  {
		  System.out.println("Executed Test method - TestMethodD");
	  }

}
