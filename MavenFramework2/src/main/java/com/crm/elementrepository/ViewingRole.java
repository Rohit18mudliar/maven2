package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewingRole {

	/*************** Initialisation ********************/
	// @FindBy to store all webelement

	@FindBy(xpath = "//input[@name='Edit']")
	private WebElement editingRolebtn;

	public WebElement getEditingRolebtn() {
		return editingRolebtn;
	}

	
}
