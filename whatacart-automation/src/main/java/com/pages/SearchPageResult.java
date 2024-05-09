package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageResult {
	WebDriver driver= null;
	public SearchPageResult(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id ="navbarsearchform-keyword")
	WebElement searchBar;
	@FindBy(name ="navsearch")
	WebElement searchLogo;
	@FindBy(id="list-view")
	WebElement list;
	@FindBy(xpath ="//div[contains(@class,'product-layout product-list col-xs-12')]//h4")
	List<WebElement> devices;
	@FindBy(id="searchform-categoryid")
	WebElement categorySelect;
	@FindBy(id="searchresultslistview-pjax")
	WebElement textResult;
	@FindBy(id="save")
	WebElement search;
	@FindBy(id="sortBy")
	WebElement sort;
	@FindBy(xpath = "//span[@class='price-new']")
	List<WebElement> prices;
	@FindBy(xpath="//div[@data-key=\"0\"]//h4")
	WebElement appleClick;
	
	public void searchItems(String item) {
		searchBar.sendKeys(item);
		searchLogo.click();
		list.click();
	}
	
	public List<String> verifyDevices() {
		List<String> actualResult = new ArrayList<String>();
		for(WebElement device: devices) {
			String text = device.getText();
			actualResult.add(text);
		}
		return actualResult;
	}
	
	public String searchCategory(String item,String value) {
		searchBar.sendKeys(item);
		searchLogo.click();
		Select sl = new Select(categorySelect);
		sl.selectByValue(value);
		search.click();		
		list.click();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(textResult));
		String text = textResult.getText();
		return text;
	}

	public List<String> devicePriceList(){
		
		List<String> actaulPriceList = new ArrayList<String>();
		for(WebElement price:prices) {
			String text = price.getText();
			actaulPriceList.add(text);
		}
		return actaulPriceList;
	}
	
	public void searchAndSort(String item, String value) {
		searchBar.sendKeys(item);
		searchLogo.click();
		list.click();
		Select sl= new Select(sort);
		sl.selectByValue(value);
	}
	
	public ProductPage searchDesktopAndClick(String item) {
		searchBar.sendKeys(item);
		searchLogo.click();
		list.click();
		appleClick.click();
		return new ProductPage(driver);
	}
	
	
	
}
