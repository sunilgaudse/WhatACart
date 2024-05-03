package com.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utilities.PropertyUtils;

public class BaseClass {
	public static WebDriver driver = null;
	public static ExtentReports report = null;
	public static ExtentSparkReporter spark = null;
	public static ExtentTest test = null;
	public static Logger log = Logger.getLogger(BaseClass.class);

	public static void intialization() throws Exception {
		log.info("Reading property file for browser");// for file
		System.out.println("Reading property file for browser");// for console
		String browserName = PropertyUtils.readProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("wedriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			driver = new ChromeDriver();
			log.info("Chrome browser is initializing....");
		}
		if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox browser is initializing....");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(PropertyUtils.readProperty("url"));
	}

	public void reportInIt() {
		report = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/ExtentReports.html");
		report.attachReporter(spark);
	}
}
