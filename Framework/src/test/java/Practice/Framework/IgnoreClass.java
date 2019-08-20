package Practice.Framework;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class IgnoreClass
{
	@Test
	public void test1()
	{
		System.out.println("Executed method test1 in IgnoreClass");
	}
	
	@Test
	public void test2()
	{
		System.out.println("Executed method test2 in IgnoreClass");
	}
	
	@Test
	public void test3()
	{
		System.out.println("Executed method test3 in IgnoreClass");
	}
}
