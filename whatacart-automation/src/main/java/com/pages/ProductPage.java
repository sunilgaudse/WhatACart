package com.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	
	public static Logger log = Logger.getLogger(ProductPage.class);
	WebDriver driver = null;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Description']")
	WebElement descriptionTab;

	@FindBy(id = "tab-description")
	WebElement text;
	@FindBy(xpath = "//a[contains(text(),'Reviews')]")
	WebElement reviewTab;
	@FindBy(id = "productreview-review")
	WebElement textArea;
	@FindBy(id = "save")
	WebElement submit;
	@FindBy(xpath = "//div[@class='alert alert-success alert-review']")
	WebElement feedbackOnSubmit;
	@FindBy(xpath = "//p[text()='Review cannot be blank.']")
	WebElement blankReview;
	@FindBy(id="productreview-name")
	WebElement name;
	@FindBy(id="productreview-email")
	WebElement email;
	@FindBy(xpath="//a[text()='Specifications']")
	WebElement specsTab;
	@FindBy(xpath="//table[@class='table table-bordered']//tbody")
	WebElement table;
	
	


	public String verifyDescription() {
		descriptionTab.click();
		// WebDriverWait wait = new WebDriverWait(driver,100);
		// wait.until(ExpectedConditions.visibilityOf(text));
		return text.getText();
	}

	public String  submitReview() {
		reviewTab.click();
		name.sendKeys("Sunil Gaudase");
		email.sendKeys("sunilgaudse@gmail.com");
		textArea.sendKeys("Nice product.");
		submit.click();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(feedbackOnSubmit));
		return feedbackOnSubmit.getText();
	}
	public String nonSubmitReview() {
		
		reviewTab.click();
		submit.click();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(blankReview));
		
		return blankReview.getText();
	}
	
	public WebElement getSpecificationsTable(){
		specsTab.click();
		return table;
	}
	
}
