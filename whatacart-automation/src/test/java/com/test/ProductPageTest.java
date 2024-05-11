package com.test;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.ProductPage;
import com.pages.SearchPageResult;

public class ProductPageTest extends BaseClass {

	public static Logger log = Logger.getLogger(ProductPageTest.class);

	SearchPageResult sp = null;
	ProductPage pp = null;

	@BeforeSuite
	public void reportInitialization() {
		reportInIt();
	}

	@BeforeMethod
	public void setup(Method method) throws Exception {
		log.info("Test case execution started with name :"+ method.getName());
		intialization();
		sp = new SearchPageResult(driver);
		pp = sp.searchDesktopAndClick("Apple");
		
	}

	@Test
	public void verifyDescriptionText() {
		String actualText = pp.verifyDescription();
		Assert.assertEquals(actualText, "This is description for product Apple Cinema 20\"");

	}

	@Test
	public void verifyIsReviewSubmittedWithoutLogIn() {
		String actualText = pp.submitReview();
		log.info(actualText);
		String expectedText = "Thank you for your review. It has been submitted to the admin for approval.";

		Assert.assertEquals(actualText, expectedText);

	}

	@Test
	public void verifySpecification() {
		String[][] expectedData = { { "Fan Speed", "300 RPM" } };
		WebElement table = pp.getSpecificationsTable();
		List<WebElement> rows = table.findElements(By.xpath("tr"));
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

			Assert.assertEquals(cells.size(), expectedData[i].length,
					"Number of cells in row " + i + " does not match");
			for (int j = 0; j < cells.size(); j++) {
				String cellText = cells.get(j).getText();
				log.info(cellText);
				Assert.assertEquals(cellText, expectedData[i][j], "Mismatch in row " + i + ", cell " + j);
			}

		}
	}
	
	@Test
	public void verifyBlankReviewText() {
		String blankText=pp.nonSubmitReview();
		Assert.assertEquals(blankText, "Review cannot be blank.");
	}

	@AfterMethod
	public void teardown(Method method) {
		log.info("Test case execution completed with name :"+ method.getName());
		log.info("==================================================================================");
		driver.quit();
	}

	@AfterSuite
	public void closeReport() {
		report.flush();
	}
}
