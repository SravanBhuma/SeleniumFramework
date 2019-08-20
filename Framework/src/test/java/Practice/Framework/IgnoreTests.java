package Practice.Framework;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class IgnoreTests 
{
	@Test
	public void test1()
	{
		System.out.println("Executed method test1");
	}
	
	@Ignore
	@Test
	public void test2()
	{
		System.out.println("Executed method test2");
	}
	
	@Test
	public void test3()
	{
		System.out.println("Executed method test3");
	}
}
