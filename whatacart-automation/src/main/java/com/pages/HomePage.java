package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver = null;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//a[text()='Login']")
	WebElement logInBtn;
	
	public LoginPage loginBtn() {
		myAccount.click();
		logInBtn.click();
		return new LoginPage(driver);
	}
}

