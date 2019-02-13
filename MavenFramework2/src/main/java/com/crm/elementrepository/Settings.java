package com.crm.elementrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Settings {

	/*************** Initialisation ********************/
	// @FindBy to store all webelement

	@FindBy(xpath =  "//a[contains(text(),'Profiles')]")
	private WebElement profilesbtn;
	
	@FindBy(xpath = "//a[contains(text(),'Roles')]")
	private WebElement rolesbtn;
	
	

	/*************** Business Methods ********************/

	public void navigateToProfiles() {
		
		profilesbtn.click();
		
	}
	
	public void navigateToRoles() {
		
		rolesbtn.click();
		
	}

}
