package com.test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.ProductPage;
import com.pages.SearchPageResult;



public class ProductPageTest  extends BaseClass{

	public static Logger log =Logger.getLogger(ProductPageTest.class);
	
	SearchPageResult sp =null;
	ProductPage pp =null;
	
	@BeforeSuite
	public void reportInitialization() {
		reportInIt();
	}
	@BeforeMethod
	public void setup() throws Exception {
		intialization();
		sp= new SearchPageResult(driver);
		pp= sp.searchDesktopAndClick("Apple");
		log.info("Test case execution started");
		
	}
	
	@Test
	public void verifyDescriptionText() {
		String actualText =pp.verifyDescription();
		
		Assert.assertEquals(actualText, "This is description for product Apple Cinema 20\"");
		
	}
	
	@AfterMethod
	public void teardown() {
		log.info("Test case execution completed");
		driver.quit();
	}
	
	@AfterSuite
	public void closeReport() {
		report.flush();
	}
}
