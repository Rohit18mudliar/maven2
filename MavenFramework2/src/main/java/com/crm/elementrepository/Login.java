package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {

	
	/***************Initialisation********************/
	//@FindBy to store all webelement
	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;

	
	
	
	/***************Business Methods********************/
	
	public void loginToApp(String username,String password) {
		
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginbtn.click();
		
		
	}
	
	
	
	
	
}
