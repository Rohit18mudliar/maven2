package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home {

	/***************Initialisation********************/
	// @FindBy to store all webelement

	@FindBy(xpath = "//img[@src='themes/softed/images/mainSettings.PNG']")
	private WebElement settingsbtn;

	@FindBy(xpath = "//a[text()='CRM Settings']")
	private WebElement crmsettingsbtn;
	
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement userbtn;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signoutbtn;
	
	
	
	/***************Business Methods********************/
	
	
	public void navigateToSettings() {
		settingsbtn.click();
		crmsettingsbtn.click();

	}
	
	public void logout() {
		userbtn.click();
		signoutbtn.click();

	}
	
	

	
	
	
	
}
