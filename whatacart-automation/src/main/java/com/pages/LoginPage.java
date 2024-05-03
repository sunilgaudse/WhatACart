package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver= null;
	public LoginPage() {
		
	}
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="loginform-username")
	WebElement username;
	
	@FindBy(id="loginform-password")
	WebElement password;
	
	@FindBy(id="savebutton")
	WebElement login;
	
	public void loginToApplication(String uname, String pass) {
		username.sendKeys(uname);
		password.sendKeys(pass);
		login.click();
	}
	
}
