package com.crm.commonlib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils 
{
	/**
	 * Wait for Page to load
	 */
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//System.out.println("\n Page is loaded in After Implicit Wait\n");
	}
	/**
	 * Wait for any element is available in GUI 
	 */
	public void waitForElementPresent(WebDriver driver, WebElement wb)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(wb));
		//System.out.println("\nElement is Visible  After Explicit Wait\n");
	}
	/**
	 * Custom Wait
	 * @throws InterruptedException 
	 */
	public void waitForCompleteElementToLoad(WebElement wb) throws InterruptedException
	{
		int count = 0;
		while(count<10)
		{
			try
			{
				wb.isDisplayed();
				//System.out.println("\nElement is displayed\n");
				break;
			}
			catch(Throwable t)
			{
				System.out.println("Handle the exception & continue");
				Thread.sleep(1000);
				count++;
			}
		}
	}
}
