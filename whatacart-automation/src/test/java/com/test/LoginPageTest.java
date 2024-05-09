package com.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	public static Logger log = Logger.getLogger(LoginPageTest.class);

	HomePage hp = null;
	LoginPage lp = null;

	@BeforeSuite
	public void reportinitialize() {
		reportInIt();
	}

	@BeforeMethod
	public void setup() throws Exception {
		intialization();

		hp = new HomePage(driver);
		lp = hp.loginBtn();
	}

	@Test
	public void verifyLoginSuccessfull() throws InterruptedException {
		log.info("verifyLoginSuccessfull test case execution started.");
		lp.loginToApplication("sunilgaudse", "Sarika@20001994");
			
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.urlToBe("https://ingecnotechnologies.com/other/Wcart/"));
		WebElement dropdown = driver.findElement(By.xpath("//a[@title='My Account']"));
		dropdown.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
		String logout = driver.findElement(By.xpath("//a[text()='Logout']")).getText();
		log.info("Expected text is : " + logout);
		Assert.assertEquals(logout, "Logout");
		log.info("verifyLoginSuccessfull test case execution completed");
		

	}

	@Test
	public void verifyLoginFailedOnInvalidCred() {
		log.info("verifyLoginFailedOnInvalidCred test  case execution started.");
		lp.loginToApplication("sunilgaudase", "Sunil@94");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement warnText = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[text()='The credentials passed are not valid.']")));
		log.info("Expected text are : "+ warnText.getText());
		Assert.assertEquals(warnText.getText(), "The credentials passed are not valid.");
		log.info("verifyLoginFailedOnInvalidCred test case execution completed.");
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
