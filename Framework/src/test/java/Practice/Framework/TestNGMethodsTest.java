package Practice.Framework;

import org.testng.annotations.*;

public class TestNGMethodsTest 
{
  @BeforeTest
  public void MethodA()
  {
	  System.out.println("Executed Before Test method - Method A");
  }
  
  @AfterTest
  public void MethodB()
  {
	  System.out.println("Executed After Test method - Method B");
  }
  
  @BeforeSuite(alwaysRun=true)
  public void MethodC()
  {
	  System.out.println("Executed Before Suite method - Method C");
  }
  
  @AfterSuite(alwaysRun=true)
  public void MethodD()
  {
	  System.out.println("Executed After Suite method - Method D");
  }
  
  @BeforeClass
  public void MethodE()
  {
	  System.out.println("Executed Before Class method - Method E");
  }
  
  @AfterClass
  public void MethodF()
  {
	  System.out.println("Executed After Class method - Method F");
  }
  
  @Test(groups = {"sanity", "smoke"})
  public void TestMethodA()
  {
	  System.out.println("Executed Test method - TestMethodA");
  }
  
  @Test(groups={"regression", "smoke"})
  public void TestMethodB()
  {
	  System.out.println("Executed Test method - TestMethodB");
  }
	
}
