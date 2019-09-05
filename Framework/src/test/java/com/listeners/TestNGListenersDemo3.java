package com.listeners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestNGListenersDemo3
{
	@Test
	public void Test9()
	{
		System.out.println("Executed test method 9");
	}
	
	@Test
	public void Test10()
	{
		System.out.println("Executed test method 10");
	}
	
	@Test
	public void Test11()
	{
		System.out.println("Executed test method 11");
		throw new SkipException("Skipping test 11");
	}
	
	@Test
	public void Test12()
	{
		System.out.println("Executed test method 12");
		Assert.assertTrue(false);
	}

}
