package com.crm.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CreateNewRole {

	/*************** Initialisation ********************/
	
	// @FindBy to store all WebElement
	@FindBy(xpath ="//*[text()=' > Create New Role']")
	private WebElement createNewRolepage;
	
	@FindBy(xpath ="//input[@id='rolename']")
	private WebElement rolenameEdit;
	

	@FindBy(xpath ="//input[@name='Button']")
	private WebElement addEntityToMemberListbtn;
	
	@FindBy(xpath ="//input[@name='Button1']")
	private WebElement removeEntityFromMemberListbtn;
	
	public WebElement getRemoveEntityFromMemberListbtn() {
		return removeEntityFromMemberListbtn;
	}

	@FindBy(xpath ="//select[@id='selectedColumns']")
	private WebElement MemberList;
	
	
	
	
	@FindBy(xpath ="//input[@name='add']")
	private WebElement roleSavebtn;
	
	
	public WebElement getCreateNewRolepage() {
		return createNewRolepage;
	}



	public WebElement getRolenameEdit() {
		return rolenameEdit;
	}



	public WebElement getAddEntityToMemberListbtn() {
		return addEntityToMemberListbtn;
	}



	public WebElement getMemberList() {
		return MemberList;
	}



	public WebElement getRoleSavebtn() {
		return roleSavebtn;
	}


	
	
	
	
	/*************** Business Methods ********************/
	
	public void editRoleName(String rolename) throws InterruptedException {

		rolenameEdit.sendKeys(rolename);
		
	}
	
	
	
	public void addEntityToMemberList() throws InterruptedException {

		addEntityToMemberListbtn.click();
		
	}
	
	public void saveRole() throws InterruptedException {

		roleSavebtn.click();
		
	}
	
	
	
	
	
	
	
}
