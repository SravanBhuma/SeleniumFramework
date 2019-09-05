package com.listeners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestNGListenersExample.class})
public class TestNGListenersDemo 
{
	@Test
	public void Test1()
	{
		System.out.println("Executed test method 1");
	}
	
	@Test
	public void Test2()
	{
		System.out.println("Executed test method 2");
	}
	
	@Test
	public void Test3()
	{
		System.out.println("Executed test method 3");
		throw new SkipException("Skipping test 3");
	}
	
	@Test
	public void Test4()
	{
		System.out.println("Executed test method 4");
		Assert.assertTrue(false);
	}

}
