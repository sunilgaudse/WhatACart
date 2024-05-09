package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver = null;

	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//a[text()='Description']")
	WebElement description;
	
	@FindBy(id="tab-description")
	WebElement text;
	
	public String verifyDescription() {
		description.click();
		//WebDriverWait wait = new WebDriverWait(driver,100);
		//wait.until(ExpectedConditions.visibilityOf(text));
		return text.getText();
		}
}
