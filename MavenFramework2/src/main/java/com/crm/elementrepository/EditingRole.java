package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class EditingRole {

	


	/*************** Initialisation ********************/
	// @FindBy to store all webelement

	@FindBy(xpath = "//tbody/tr/td[2]/b")
	private WebElement editingRoleElement;

	public WebElement geteditingRoleElement() {
		return editingRoleElement;
	}
}
