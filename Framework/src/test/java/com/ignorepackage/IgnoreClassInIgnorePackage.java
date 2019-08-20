package com.ignorepackage;

import org.testng.annotations.Test;

public class IgnoreClassInIgnorePackage 
{
	@Test
	public void test1()
	{
		System.out.println("Executed method test1 in ignore package");
	}
	
	@Test
	public void test2()
	{
		System.out.println("Executed method test2 in ignore package");
	}
	
	@Test
	public void test3()
	{
		System.out.println("Executed method test3 in ignore package");
	}

}
