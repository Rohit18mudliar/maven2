package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.crm.commonlib.BaseClass;

public class Roles extends BaseClass{

	
	/*************** Initialisation ********************/
	// @FindBy to store all webelement

	@FindBy(xpath = "//*[text()='Roles & Hierarchy Tree']")
	private WebElement rolesHierarchyTreeText;
	
	@FindBy(xpath ="//b[text()='Organisation']")
	private WebElement organisationRole;
	
	@FindBy(xpath ="//b[text()='Organisation']/../../td[2]/div/a[1]/img[@title='Add Role']")
	private WebElement organisationAddRoleMenu;
	
	

	
	public WebElement getRolesHierarchyTreeText() {
		return rolesHierarchyTreeText;
	}




	public WebElement getOrganisationRole() {
		return organisationRole;
	}




	public WebElement getOrganisationAddRoleMenu() {
		return organisationAddRoleMenu;
	}

	
	
	/*************** Business Methods ********************/

	public void navigateToAddRole() throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(organisationRole).perform();

		wbUtil.waitForCompleteElementToLoad(organisationAddRoleMenu);
		organisationAddRoleMenu.click();
		
	}
	
	
	
	
}
