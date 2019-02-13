package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.commonlib.BaseClass;

public class ProfilesPrivileges extends BaseClass{

	/*************** Initialisation ********************/
	// @FindBy to store all webelement

	@FindBy(xpath = "//input[@name='profile_name']")
	private WebElement profilenameEdit;
	
	@FindBy(xpath = "//input[@name='Next']")
	private WebElement Nextbtn;
	
	

	/*************** Getters ********************/
	public WebElement getProfilenameEdit() {
		return profilenameEdit;
	}


	public WebElement getNextbtn() {
		return Nextbtn;
	}


	
	
	
	
	
}
