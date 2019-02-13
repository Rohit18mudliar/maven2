package com.crm.commonlib;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.crm.elementrepository.Login;

public class BaseClass {

	public static WebDriver driver;
	public static FileUtils flib = new FileUtils();
	public static WebDriverUtils wbUtil = new WebDriverUtils();
	public static SoftAssert sa=new SoftAssert();

	@BeforeSuite
	public void configBS() throws Throwable {
	
		System.out.println("******Connect to DB***********\n***Before Suite");
	}
	
	@AfterSuite
	public void configAS() throws Throwable {
	
		System.out.println("******Disconnect to DB***********\n***After Suite");
		
		System.out.println("******Take Report Backup***********\n***After Suite");
		String cdate=new Date().toString().replace(" ", "_").replace(":", "_");
		
		File dstFile=new File(".//backup//"+cdate+"_Batch_result.html");
		File srcFile=new File(".//target//surefire-reports//emailable-report.html");
		org.apache.commons.io.FileUtils.copyFile(srcFile, dstFile);
		
		
		
	}
	
	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("===Launch Browser====");
		Properties pobj = flib.getPropertyObject();
		String browserName = pobj.getProperty("browser");

		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", "./resources/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("firefox 45")) {

			driver = new FirefoxDriver();

		} else if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("IE")) {

			System.setProperty("webdriver.ie.driver", "./resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}
		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("Login");

		// Read common from properties file
		Properties pobj = flib.getPropertyObject();

		wbUtil.waitForPageToLoad(driver);

		driver.get(pobj.getProperty("url"));

		Login lp=PageFactory.initElements(driver, Login.class);
		lp.loginToApp(pobj.getProperty("username"), pobj.getProperty("password"));

	}

	@AfterMethod
	public void configAM() throws Throwable {
		System.out.println("Logout");

		/************ Logout *****************/

		String x1 = "//img[@src='themes/softed/images/user.PNG']";
		WebElement w = driver.findElement(By.xpath(x1));
		wbUtil.waitForCompleteElementToLoad(w);
		w.click();

		x1 = "//a[text()='Sign Out']";
		WebElement w2 = driver.findElement(By.xpath(x1));
		wbUtil.waitForCompleteElementToLoad(w2);
		w2.click();

	}

	@AfterClass
	public void configAC() {
		System.out.println("=====Close Browser=======");
		driver.close();
	}

}
