package com.listeners;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utilities.DriverUtils;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners extends BaseClass implements ITestListener{
 public static Logger log = Logger.getLogger(MyListeners.class);
	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getName());	
		log.info("Test case " + result.getName() + "execution is started.");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test case success with name " + result.getName());
		log.info("Test case success with name " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.FAIL, "Test case failed with name " + result.getName());
		log.info("Test case failed with name " + result.getName());
		String path = DriverUtils.captureScreenShot(result.getName());
		test.addScreenCaptureFromPath(path);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP, "Test case skipped with name "+result.getName());
		log.info("Test case skipped with name " + result.getName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//report.flush();
	}

}
