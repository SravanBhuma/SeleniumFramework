package Practice.Framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ggktech.service.RetryAnalyzer;

public class RetryFailedExample 
{
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void test1()
	{
		Assert.fail("Test1 method in Retry Failed Example class is failed");
		System.out.println("Executed method test1 in ignore package");
	}
	
	@Test
	public void test2()
	{
//		Assert.fail("Test2 method in Retry Failed Example class is failed");
		System.out.println("Executed method test2 in ignore package");
	}
	
	@Test
	public void test3()
	{
		System.out.println("Executed method test3 in ignore package");
	}

}
