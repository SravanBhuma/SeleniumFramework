package Practice.Framework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependancyExample 
{
	
	//	when both priority and dependency are present then dependency will take the first preference
	@Test(priority = 1, dependsOnMethods = {"test2", "test3"})
	public void test1()
	{
		System.out.println("Executed method test1");
	}
	
	@Test(priority = 2, dependsOnGroups={"san.*"})
	public void test2()
	{
		System.out.println("Executed method test2");
	}
	
	
	@Test
	public void test3()
	{
		Assert.fail("Failed at test3");
		System.out.println("Executed method test3");
	}
	
	@Test(groups ={"sanity"})
	public void testRegular()
	{
		System.out.println("Executed method testRegular");
	}
	
	@Test(priority = 1, dependsOnMethods = {"test2", "test3"}, alwaysRun=true)
	public void test5()
	{
		System.out.println("Executed method test5");
	}

}
