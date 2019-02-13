package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.commonlib.BaseClass;

public class ViewingAccessPrivileges extends BaseClass{

	/*************** Initialisation ********************/
	// @FindBy to store all webelement

	@FindBy(xpath =  "//table/tbody/tr[1]/td[1]/table/tbody/tr/td[2]/input[@name='save' and @type='submit']")
	private WebElement finishbtn;

	public WebElement getFinishbtn() {
		return finishbtn;
	}
	
	
	
}
