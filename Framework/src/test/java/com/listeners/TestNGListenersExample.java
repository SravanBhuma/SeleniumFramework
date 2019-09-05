package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListenersExample implements ITestListener
{

	@Override
	public void onFinish(ITestContext arg0) 
	{
		System.out.println("Test cases under test: '"+ arg0.getName() +"' finished");
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Test cases under test: '"+ arg0.getName() +"' started");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) 
	{
		System.out.println("Test case failed: "+ arg0.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Test case skipped: "+ arg0.getName());
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Test case succeeded: "+ arg0.getName());
		
	}

}
