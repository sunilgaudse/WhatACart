package com.retryanalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		int count =0;
		int  maxCount=3;
		if(count<maxCount) {
			count++;
			return true;
		}
		return false;
	}

}
