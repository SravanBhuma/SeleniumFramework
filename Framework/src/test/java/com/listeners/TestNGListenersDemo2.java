package com.listeners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestNGListenersDemo2 
{
	@Test
	public void Test5()
	{
		System.out.println("Executed test method 5");
	}
	
	@Test
	public void Test6()
	{
		System.out.println("Executed test method 6");
	}
	
	@Test
	public void Test7()
	{
		System.out.println("Executed test method 7");
		throw new SkipException("Skipping test 7");
	}
	
	@Test
	public void Test8()
	{
		System.out.println("Executed test method 8");
		Assert.assertTrue(false);
	}
}
