package com.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.SearchPageResult;
import com.retryanalyzer.RetryAnalyser;

public class SearchPageTest extends BaseClass {
	SearchPageResult result = null;

	@BeforeSuite
	public void reportinitialize() {
		reportInIt();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		intialization();
		result = new SearchPageResult(driver);
	}

	@Test(dataProvider = "devices", retryAnalyzer = RetryAnalyser.class)
	public void verifySearchResults(String deviceName) {
		List<String> expectedResult = null;
		if (deviceName == "Apple") {
			expectedResult = List.of("Apple Cinema 20\"", "Apple Cinema 21\"", "Apple Cinema 22\"", "Apple Cinema 23\"",
					"Apple Cinema 24\"", "Apple Cinema 25\"", "Apple Cinema 26\"", "Apple Cinema 27\"");
			result.searchItems(deviceName);
		}
		if (deviceName == "Sony") {
			expectedResult = List.of("Sony Vaio 20\"", "Sony Vaio 21\"", "Sony Vaio 22\"", "Sony Vaio 23\"",
					"Sony Vaio 24\"", "Sony Vaio 25\"", "Sony Vaio 26\"", "Sony Vaio 27\"");
			result.searchItems(deviceName);
		}
		if (deviceName == "Canon") {
			expectedResult = List.of("Canon EOS 5D", "Canon EOS 5 S", "Canon EOS 5 LX", "Canon EOS 6D", "Canon EOS 6 S",
					"Canon EOS 6 LX", "Canon EOS 7D", "Canon EOS 7 S");
			result.searchItems(deviceName);
		}

		List<String> actualResult = result.verifyDevices();
		Assert.assertEquals(actualResult, expectedResult);
	}

	@Test(dataProvider = "category", retryAnalyzer = RetryAnalyser.class)
	public void verifyDevicesWithCategory(String deviceValue) {

		if (deviceValue == "1") {
			List<String> expectedResult = List.of("Apple Cinema 20\"", "Apple Cinema 21\"", "Apple Cinema 22\"",
					"Apple Cinema 23\"", "Apple Cinema 24\"", "Apple Cinema 25\"", "Apple Cinema 26\"",
					"Apple Cinema 27\"");
			result.searchCategory("Apple", deviceValue);
			List<String> actualResult = result.verifyDevices();
			Assert.assertEquals(actualResult, expectedResult);
		}

		if (deviceValue == "2") {
			String expectedResult1 = result.searchCategory("Apple", deviceValue);
			Assert.assertEquals("No results found.", expectedResult1);

		}
		if (deviceValue == "3") {
			String expectedResult1 = result.searchCategory("Apple", deviceValue);
			Assert.assertEquals("No results found.", expectedResult1);
		}
	}

	@Test(retryAnalyzer = RetryAnalyser.class)
	public void verifySortByDescendingOrder() {
		List<String> expectedResult = List.of("Apple Cinema 30\"","Apple Cinema 29\"", "Apple Cinema 28\"", "Apple Cinema 27\"",
				"Apple Cinema 26\"", "Apple Cinema 25\"", "Apple Cinema 24\"", "Apple Cinema 23\"" 
				);
		result.searchAndSort("Apple", "namedesc");
		List<String> actualDescendingList = result.verifyDevices();
		Assert.assertEquals(actualDescendingList, expectedResult);
	}
	
	@Test(retryAnalyzer = RetryAnalyser.class)
	public void verifySortByPriceLowToHigh() {
		List<String> expectedPriceList =List.of("$20.00","$30.00","$40.00","$50.00","$60.00","$70.00","$80.00","$90.00");
		result.searchAndSort("Apple", "priceasc");
		List<String> actualLowToHighPrice = result.devicePriceList();
		Assert.assertEquals(actualLowToHighPrice, expectedPriceList);
	}
	
	@Test(retryAnalyzer = RetryAnalyser.class)
	public void verifySortByPriceHighToLow() {
		List<String> expectedPriceList =List.of("$110.00","$10.00","$100.00","$90.00","$80.00","$70.00","$60.00","$50.00");
		result.searchAndSort("Apple", "pricedesc");
		List<String> actualLowToHighPrice = result.devicePriceList();
		Assert.assertEquals(actualLowToHighPrice, expectedPriceList);
	}

	@DataProvider(name = "devices")
	public Object[] devices() {
		return new Object[] { "Apple", "Sony", "Canon" };

	}

	@DataProvider(name = "category")
	public Object[] categoryValue() {
		return new Object[] { "1", "2", "3" };
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@AfterSuite
	public void closeReport() {
		report.flush();
	}
}