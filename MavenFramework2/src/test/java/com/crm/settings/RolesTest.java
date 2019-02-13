package com.crm.settings;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.commonlib.BaseClass;
import com.crm.commonlib.FileUtils;
import com.crm.elementrepository.CreateNewRole;
import com.crm.elementrepository.EditingRole;
import com.crm.elementrepository.Home;
import com.crm.elementrepository.Profiles;
import com.crm.elementrepository.ProfilesPrivileges;
import com.crm.elementrepository.Roles;
import com.crm.elementrepository.Settings;
import com.crm.elementrepository.ViewingAccessPrivileges;
import com.crm.elementrepository.ViewingRole;

@Listeners( com.crm.commonlib.ListenerImpClass.class)
public class RolesTest extends BaseClass {
	// TestNG Class

	@Test(priority = 1, groups = { "SmokeTest" })
	public void createRoles()throws Throwable {


			/********** Test data **********/

			String x1, expextedResult, actualResult, expectedPage, profile_name, roleName;

			boolean flag = false, flag2 = false, status;

			Random num = new Random();
			
		 
			

			// Read common from Excel file
			FileUtils flib = new FileUtils();
			roleName = flib.getExcelData("Sheet1", 2, 10);
			roleName = roleName + "_" + num.nextInt(100) + num.nextInt(100);

			profile_name = flib.getExcelData("Sheet1", 2, 11);
			profile_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);

			/*--------------------------------------------*/
			

				/************ Create Profile to Select *****************/

				System.out.println("Going to Create Profile... " + profile_name);

				/************ Navigate to CRM Settings *****************/
				
				Home hp=PageFactory.initElements(driver, Home.class);
				hp.navigateToSettings();

				System.out.println("In CRM Settings");

				/************ Navigate to Profiles *****************/
				
				
				Settings sp=PageFactory.initElements(driver, Settings.class);
				sp.navigateToProfiles();
				
				System.out.println("In Profiles");

				
				// Click on New Profile Button
				
				Profiles pp=PageFactory.initElements(driver, Profiles.class);
				pp.navigateToNewProfiles();
				
				
				// wbUtil.waitForElementPresent(driver, wb);
			
				System.out.println("After Clicking on New Profile button");

				System.out.println("In Create New Profile");

				// Enter Profile Name
				
				ProfilesPrivileges cpp=PageFactory.initElements(driver, ProfilesPrivileges.class);
				cpp.getProfilenameEdit().sendKeys(profile_name);
				
				
				
				// Click On Next
				cpp.getNextbtn().click();
				
				System.out.println("In Create New Profile ...Next Clicked");

			
				// Click on Finish
				ViewingAccessPrivileges vap=PageFactory.initElements(driver, ViewingAccessPrivileges.class);
				WebElement wb=vap.getFinishbtn();
				wbUtil.waitForCompleteElementToLoad(wb);
				wb.click();
				
				
				System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_name);

				/************ Verify Profile Created *****************/

				x1 = "//*[text()='" + profile_name + "']";
				 status=driver.findElement(By.xpath(x1)).isDisplayed();
				
				Assert.assertTrue(status);
				{

					System.out.println("Profile created.. " + profile_name);
				} 
				
				/************ Creating Role *****************/

				/************ Navigate to CRM Settings *****************/
				
				hp.navigateToSettings();

				System.out.println("In CRM Settings");


				/************ Step 3 : Navigate to Roles *****************/
				
				sp.navigateToRoles();
				

				/************ Step 3.1 : Verify Roles displayed *****************/

				
				Roles rp=PageFactory.initElements(driver, Roles.class);
				status=rp.getRolesHierarchyTreeText().isDisplayed();
					
				sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 



			

				/************ Step 4 : Navigate to Create New Roles *****************/

				// Move mouse cursor to Organisation
					
				WebElement wb1 = rp.getOrganisationRole();
				
				// wbUtil.waitForElementPresent(driver, wb1);

				

				Actions act = new Actions(driver);
				act.moveToElement(wb1).perform();

				System.out.println("after moving mouse cursor!");
				// click on Add role image option

				wb1 = rp.getOrganisationAddRoleMenu();

				wbUtil.waitForCompleteElementToLoad(wb1);
				wb1.click();
				System.out.println("After clicking on add role!");

				/************ Step 4.1 : Verify Create New Roles displayed *****************/


					CreateNewRole cnp=PageFactory.initElements(driver, CreateNewRole.class);
					status=cnp.getCreateNewRolepage().isDisplayed();
					
					
					sa.assertTrue(status," Create New Roles  does not  open >> Fail"); 
					
				

				/************ Step 5 : Create New Roles: *****************/

				// Enter Role name
					cnp.editRoleName(roleName);
				System.out.println("New Role Name entered " + roleName);

				// Click on profile created
				x1 = "//select[@id='availList']/option[text()='" + profile_name + "']";
				driver.findElement(By.xpath(x1)).click();
				
				System.out.println("Profile Selected >>" + profile_name);

				// click Button ">>"
				
				cnp.addEntityToMemberList();

				// Verify Member list

							WebElement wb2 = 	cnp.getMemberList();
							
							expextedResult = profile_name;
							Select sel = new Select(wb2);
							List<WebElement> li = sel.getOptions();
							flag=false;
							for (WebElement wb3 : li) {
								if (expextedResult.equals(wb3.getText())) {
									System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
									flag = true;
									break;
								}
							}
							Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");

				// Click Save Button
				cnp.saveRole();
				
				System.out.println(" Save button Clicked ..");

				// Verify Role Created or not

				
					x1 = "//*[text()='" + roleName + "']";
					status=driver.findElement(By.xpath(x1)).isDisplayed();
					
					Assert.assertTrue(status);
					System.out.println(" \n Role " + roleName + "  created >>pass !!!");

					sa.assertAll();

		}


	

	@Test(priority = 2, groups = { "RegressionTest" })
	public void EditRoleName() throws Throwable{


		/********** Test data **********/
		String x1, expextedResult, actualResult, expectedPage, profile_name, roleName;
		boolean flag = false, status;

		Random num = new Random();

		// Read Test Data from Excel file

		roleName = flib.getExcelData("Sheet1", 2, 10);
		roleName = roleName + "_" + num.nextInt(100) + num.nextInt(100);

		profile_name = flib.getExcelData("Sheet1", 2, 11);
		profile_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);

		/*--------------------------------------------*/

		/************ Create Profile to Select *****************/

		System.out.println("Going to Create Profile... " + profile_name);


		/************ Navigate to CRM Settings *****************/
		Home hp=PageFactory.initElements(driver, Home.class);
		hp.navigateToSettings();

		System.out.println("In CRM Settings");

		/************ Navigate to Profiles *****************/
		Settings sp=PageFactory.initElements(driver, Settings.class);
		sp.navigateToProfiles();
		
		System.out.println("In Profiles");

		
		// Click on New Profile Button
		
		Profiles pp=PageFactory.initElements(driver, Profiles.class);
		pp.navigateToNewProfiles();
		
		
		// wbUtil.waitForElementPresent(driver, wb);
	
		System.out.println("After Clicking on New Profile button");

		System.out.println("In Create New Profile");

		// Enter Profile Name
		
		ProfilesPrivileges cpp=PageFactory.initElements(driver, ProfilesPrivileges.class);
		cpp.getProfilenameEdit().sendKeys(profile_name);
		
		
		
		// Click On Next
		cpp.getNextbtn().click();
		
		System.out.println("In Create New Profile ...Next Clicked");

	
		
		// Click on Finish
		ViewingAccessPrivileges vap=PageFactory.initElements(driver, ViewingAccessPrivileges.class);
		
		WebElement wb=vap.getFinishbtn();
		wbUtil.waitForCompleteElementToLoad(wb);
		vap.getFinishbtn().click();
		
		
		System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_name);

		/************ Verify Profile Created *****************/

		x1 = "//*[text()='" + profile_name + "']";
		 status=driver.findElement(By.xpath(x1)).isDisplayed();
		
		Assert.assertTrue(status);
		{

			System.out.println("Profile created.. " + profile_name);
		} 
		

		

		/************ Create Role first *****************/

		/************ Navigate to CRM Settings *****************/
		
		hp.navigateToSettings();

		System.out.println("In CRM Settings");


		/************ Step 3 : Navigate to Roles *****************/
		
		sp.navigateToRoles();
		


		/************ Step 3.1 : Verify Roles displayed *****************/

		
		Roles rp=PageFactory.initElements(driver, Roles.class);
		status=rp.getRolesHierarchyTreeText().isDisplayed();
			
			sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
			
	

		/************ Step 4 : Navigate to Create New Roles *****************/

		// Move mouse cursor to Organisation
			
		WebElement wb1 = rp.getOrganisationRole();
		
		// wbUtil.waitForElementPresent(driver, wb1);

		

		Actions act = new Actions(driver);
		act.moveToElement(wb1).perform();

		System.out.println("after moving mouse cursor!");
		// click on Add role image option

		wb1 = rp.getOrganisationAddRoleMenu();

		wbUtil.waitForCompleteElementToLoad(wb1);
		wb1.click();
		System.out.println("After clicking on add role!");

		/************ Step 4.1 : Verify Create New Roles displayed *****************/


			CreateNewRole cnp=PageFactory.initElements(driver, CreateNewRole.class);
			status=cnp.getCreateNewRolepage().isDisplayed();
			
			
			sa.assertTrue(status," Create New Roles  does not  open >> Fail"); 
			
		

		/************ Step 5 : Create New Roles: *****************/

		// Enter Role name
			cnp.editRoleName(roleName);
		System.out.println("New Role Name entered " + roleName);

		// Click on profile created
		x1 = "//select[@id='availList']/option[text()='" + profile_name + "']";
		driver.findElement(By.xpath(x1)).click();
		
		System.out.println("Profile Selected >>" + profile_name);

		// click Button ">>"
		
		cnp.addEntityToMemberList();

		// Verify Member list

					WebElement wb2 = 	cnp.getMemberList();
					
					expextedResult = profile_name;
					Select sel = new Select(wb2);
					List<WebElement> li = sel.getOptions();
					flag=false;
					for (WebElement wb3 : li) {
						if (expextedResult.equals(wb3.getText())) {
							System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
							flag = true;
							break;
						}
					}
					Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");

		// Click Save Button
		cnp.saveRole();
		
		System.out.println(" Save button Clicked ..");

		// Verify Role Created or not

		
			x1 = "//*[text()='" + roleName + "']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();
			
			Assert.assertTrue(status);
			System.out.println(" \n Role " + roleName + "  created >>pass !!!");

			


		/************ After Creating Role ,Edit the same role*****************/
		
		/************ Step 2 : Navigate to CRM Settings *****************/
			hp.navigateToSettings();

			System.out.println("In CRM Settings");

		/************ Step 3 : Navigate to Roles *****************/

			sp.navigateToRoles();
			

		/************ Step 3.1 : Verify Roles page displayed *****************/


			status=rp.getRolesHierarchyTreeText().isDisplayed();
				
				sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
				

		/************ Step 4 : Navigate to Editing Role *****************/

		// Move mouse cursor

		x1 = "//a[text()='" + roleName + "']";
		WebElement wb11 = driver.findElement(By.xpath(x1));
		
				
		wbUtil.waitForCompleteElementToLoad(wb11);
		
		Actions act1 = new Actions(driver);
		act1.moveToElement(wb11).perform();

		// click on Edit role image option

		x1 = "//a[text()='" + roleName + "']/../../td[2]/div/a[2]/img[@title='Edit Role']";
		wb11 = driver.findElement(By.xpath(x1));

		status = driver.findElement(By.xpath(x1)).isDisplayed();

		Assert.assertTrue(status);

		wbUtil.waitForCompleteElementToLoad(wb11);

		wb11.click();

		/************ Step 4.1 : Verify Editing Role displayed *****************/

		
		EditingRole ep=PageFactory.initElements(driver, EditingRole.class);
		wbUtil.waitForCompleteElementToLoad(ep.geteditingRoleElement());
		String s = 	ep.geteditingRoleElement().getText();

		status = s.contains("> Editing");

		sa.assertTrue(status," Editing Page does not opens >> Fail");

		/************ Step 5 : Edit New Roles: *****************/

		String newRoleName = flib.getExcelData("Sheet1", 2, 12);
		roleName = newRoleName + "_" + num.nextInt(100) + num.nextInt(100);

		// Edit Role name
		

		cnp.getRolenameEdit().clear();
		cnp.getRolenameEdit().sendKeys(roleName);
		System.out.println("New Role Name is " + roleName);

		
			// Click Save Button
			cnp.getRoleSavebtn().click();
			
			System.out.println("Clicked on Save Button");
		
		

		/************ Step 5.1: Verify Roles page displayed *****************/

			status=rp.getRolesHierarchyTreeText().isDisplayed();
			
			sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 


		
		
		// Verify Edited RoleName
		x1 = "//*[contains(text(),'" + roleName + "')]";

		status = driver.findElement(By.xpath(x1)).getText().contains(roleName);

		Assert.assertTrue(status);

		System.out.println(" \n Role " + roleName + "  After Edit created >>pass !!!");
		
		sa.assertAll();
	}


	

	@Test(priority = 3, groups = { "RegressionTest" })
	public void EditRoleNameUsingEditButton()throws Throwable {


		/********** Test data **********/
		String x1, expextedResult, actualResult, expectedPage, profile_name, roleName;
		boolean flag = false, status;

		// Read common from properties file
		FileUtils flib = new FileUtils();

		Random num = new Random();

		// Read common from Excel file

		roleName = flib.getExcelData("Sheet1", 2, 10);
		roleName = roleName + "_" + num.nextInt(100) + num.nextInt(100);

		profile_name = flib.getExcelData("Sheet1", 2, 11);
		profile_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);

		/*--------------------------------------------*/

		/************ Create Profile to Select *****************/

		System.out.println("Going to Create Profile... " + profile_name);


		/************ Navigate to CRM Settings *****************/
		Home hp=PageFactory.initElements(driver, Home.class);
		hp.navigateToSettings();

		System.out.println("In CRM Settings");

		/************ Navigate to Profiles *****************/
		Settings sp=PageFactory.initElements(driver, Settings.class);
		sp.navigateToProfiles();
		
		System.out.println("In Profiles");

		
		// Click on New Profile Button
		
		Profiles pp=PageFactory.initElements(driver, Profiles.class);
		pp.navigateToNewProfiles();
		
		
		// wbUtil.waitForElementPresent(driver, wb);
	
		System.out.println("After Clicking on New Profile button");

		System.out.println("In Create New Profile");

		// Enter Profile Name
		
		ProfilesPrivileges cpp=PageFactory.initElements(driver, ProfilesPrivileges.class);
		wbUtil.waitForCompleteElementToLoad(cpp.getProfilenameEdit());
		cpp.getProfilenameEdit().sendKeys(profile_name);
		
		
		
		// Click On Next
		cpp.getNextbtn().click();
		
		System.out.println("In Create New Profile ...Next Clicked");

	
		
		// Click on Finish
		ViewingAccessPrivileges vap=PageFactory.initElements(driver, ViewingAccessPrivileges.class);
		WebElement wb=vap.getFinishbtn();
		wbUtil.waitForCompleteElementToLoad(wb);
		
		
		vap.getFinishbtn().click();
		
		
		System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_name);

		/************ Verify Profile Created *****************/

		x1 = "//*[text()='" + profile_name + "']";
		 status=driver.findElement(By.xpath(x1)).isDisplayed();
		
		Assert.assertTrue(status);
		{

			System.out.println("Profile created.. " + profile_name);
		} 
		

		

		/************ Create Role first *****************/

		/************ Navigate to CRM Settings *****************/
		
		hp.navigateToSettings();

		System.out.println("In CRM Settings");


		/************ Step 3 : Navigate to Roles *****************/
		
		sp.navigateToRoles();
		


		/************ Step 3.1 : Verify Roles displayed *****************/

		
		Roles rp=PageFactory.initElements(driver, Roles.class);
		status=rp.getRolesHierarchyTreeText().isDisplayed();
			
			sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
			
	

		/************ Step 4 : Navigate to Create New Roles *****************/

		// Move mouse cursor to Organisation
			
		WebElement wb1 = rp.getOrganisationRole();
		
		// wbUtil.waitForElementPresent(driver, wb1);

		

		Actions act = new Actions(driver);
		act.moveToElement(wb1).perform();

		System.out.println("after moving mouse cursor!");
		// click on Add role image option

		wb1 = rp.getOrganisationAddRoleMenu();

		wbUtil.waitForCompleteElementToLoad(wb1);
		wb1.click();
		System.out.println("After clicking on add role!");

		/************ Step 4.1 : Verify Create New Roles displayed *****************/

			x1 = "//*[text()=' > Create New Role']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();

			CreateNewRole cnp=PageFactory.initElements(driver, CreateNewRole.class);
			status=cnp.getCreateNewRolepage().isDisplayed();
			
			
			sa.assertTrue(status," Create New Roles  does not  opens >> Fail"); 
			

		

		/************ Step 5 : Create New Roles: *****************/

		// Enter Role name
			cnp.editRoleName(roleName);
		System.out.println("New Role Name entered " + roleName);

		// Click on profile created
		x1 = "//select[@id='availList']/option[text()='" + profile_name + "']";
		driver.findElement(By.xpath(x1)).click();
		
		System.out.println("Profile Selected >>" + profile_name);

		// click Button ">>"
		
		cnp.addEntityToMemberList();

		// Verify Member list

					WebElement wb2 = 	cnp.getMemberList();
					
					expextedResult = profile_name;
					Select sel = new Select(wb2);
					List<WebElement> li = sel.getOptions();
					flag=false;
					for (WebElement wb3 : li) {
						if (expextedResult.equals(wb3.getText())) {
							System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
							flag = true;
							break;
						}
					}
					Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");

		// Click Save Button
		cnp.saveRole();
		
		System.out.println(" Save button Clicked ..");

		// Verify Role Created or not

		
			x1 = "//*[text()='" + roleName + "']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();
			
			Assert.assertTrue(status);
			System.out.println(" \n Role " + roleName + "  created >>pass !!!");

			

		/************ Step 2 : Navigate to CRM Settings *****************/
			hp.navigateToSettings();

			System.out.println("In CRM Settings");

		/************ Step 3 : Navigate to Roles *****************/

			sp.navigateToRoles();

		/************ Step 3.1 : Verify Roles displayed *****************/


			status=rp.getRolesHierarchyTreeText().isDisplayed();
				
				sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
				

		/************ Step 4 : Navigate to Editing Role *****************/

		// click on RoleName

		WebElement wb11 = driver.findElement(By.linkText(roleName));

		status = driver.findElement(By.xpath(x1)).isDisplayed();

		Assert.assertTrue(status);

		wbUtil.waitForCompleteElementToLoad(wb11);

		wb11.click();

		/************ Step 4.1 : Verify Editing Role displayed *****************/

		
		EditingRole ep=PageFactory.initElements(driver, EditingRole.class);
		wbUtil.waitForCompleteElementToLoad(ep.geteditingRoleElement());
		String s = 	ep.geteditingRoleElement().getText();
        System.out.println(s);
		status = s.contains("Viewing \""+roleName+"\"");

		sa.assertTrue(status,"Viewing Role page  does not open >> Fail");

		/************ Step 5 : Edit New Roles: *****************/

		String newRoleName = flib.getExcelData("Sheet1", 2, 12);
		System.out.println("Before Editing >> Role was " + roleName);
		roleName = newRoleName + "_" + num.nextInt(100) + num.nextInt(100);

		

		// click on edit button

		ViewingRole vrp=PageFactory.initElements(driver, ViewingRole.class);
		vrp.getEditingRolebtn().click();
		System.out.println("Clicked on Edit Button");

		// Edit Role name

		cnp.getRolenameEdit().clear();
		cnp.getRolenameEdit().sendKeys(roleName);
		System.out.println("After Role name edited ...New Role Name is " + roleName);

		// Click Save Button
					cnp.getRoleSavebtn().click();
					
					System.out.println("Clicked on Save Button");
		
					
					
		/************ Step 5.1: Verify Roles page displayed *****************/

					status=rp.getRolesHierarchyTreeText().isDisplayed();
					
					sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
				

		// Verify Edited RoleName
		x1 = "//*[contains(text(),'" + roleName + "')]";

		status = driver.findElement(By.xpath(x1)).getText().contains(roleName);

		Assert.assertTrue(status);

		System.out.println(" \n Role " + roleName + "  After Edit created >>pass !!!");

		sa.assertAll();
	}


	

	@Test(priority = 4, groups = { "RegressionTest" })
	public void EditRoleWithChangingProfileTest()throws Throwable {

		
	
		

		/********** Test data **********/
		String x1, expextedResult, actualResult, expectedPage, profile_name, roleName, profile_new_name;
		boolean flag = false, status;

		// Read common from properties file
		FileUtils flib = new FileUtils();

		Random num = new Random();

		// Read common from Excel file

		roleName = flib.getExcelData("Sheet1", 2, 10);
		roleName = roleName + "_" + num.nextInt(100) + num.nextInt(100);

		profile_name = flib.getExcelData("Sheet1", 2, 11);
		profile_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);
		profile_new_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);
		/*--------------------------------------------*/

		/************ Create Profile to Select *****************/

		System.out.println("Going to Create Profile... " + profile_name);


		/************ Navigate to CRM Settings *****************/
		Home hp=PageFactory.initElements(driver, Home.class);
		hp.navigateToSettings();

		System.out.println("In CRM Settings");

		/************ Navigate to Profiles *****************/
		Settings sp=PageFactory.initElements(driver, Settings.class);
		sp.navigateToProfiles();
		
		System.out.println("In Profiles");

		
		// Click on New Profile Button
		
		Profiles pp=PageFactory.initElements(driver, Profiles.class);
		pp.navigateToNewProfiles();
		
		
		// wbUtil.waitForElementPresent(driver, wb);
	
		System.out.println("After Clicking on New Profile button");

		System.out.println("In Create New Profile");

		// Enter Profile Name
		
		ProfilesPrivileges cpp=PageFactory.initElements(driver, ProfilesPrivileges.class);
		cpp.getProfilenameEdit().sendKeys(profile_name);
		
		
		
		// Click On Next
		cpp.getNextbtn().click();
		
		System.out.println("In Create New Profile ...Next Clicked");

	
		
		// Click on Finish
		ViewingAccessPrivileges vap=PageFactory.initElements(driver, ViewingAccessPrivileges.class);
		WebElement wb=vap.getFinishbtn();
		wbUtil.waitForCompleteElementToLoad(wb);
		vap.getFinishbtn().click();
		
		
		System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_name);

		/************ Verify Profile Created *****************/

		x1 = "//*[text()='" + profile_name + "']";
		 status=driver.findElement(By.xpath(x1)).isDisplayed();
		
		Assert.assertTrue(status);
		{

			System.out.println("Profile created.. " + profile_name);
		} 
		

		

		/************ Create Role first *****************/

		/************ Navigate to CRM Settings *****************/
		
		hp.navigateToSettings();

		System.out.println("In CRM Settings");


		/************ Step 3 : Navigate to Roles *****************/
		
		sp.navigateToRoles();
		


		/************ Step 3.1 : Verify Roles displayed *****************/

		
		Roles rp=PageFactory.initElements(driver, Roles.class);
		status=rp.getRolesHierarchyTreeText().isDisplayed();
			
			sa.assertTrue(status); 
			{
			
			System.out.println("Roles & Hierarchy Tree  opens >> Success");
			} 

	

		/************ Step 4 : Navigate to Create New Roles *****************/

		// Move mouse cursor to Organisation
			
		WebElement wb1 = rp.getOrganisationRole();
		
		// wbUtil.waitForElementPresent(driver, wb1);

		

		Actions act = new Actions(driver);
		act.moveToElement(wb1).perform();

		System.out.println("after moving mouse cursor!");
		// click on Add role image option

		wb1 = rp.getOrganisationAddRoleMenu();

		wbUtil.waitForCompleteElementToLoad(wb1);
		wb1.click();
		System.out.println("After clicking on add role!");

		/************ Step 4.1 : Verify Create New Roles displayed *****************/

			x1 = "//*[text()=' > Create New Role']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();

			CreateNewRole cnp=PageFactory.initElements(driver, CreateNewRole.class);
			status=cnp.getCreateNewRolepage().isDisplayed();
			

			sa.assertTrue(status," Create New Roles  does not  opens >> Fail"); 
			
		
		

		/************ Step 5 : Create New Roles: *****************/

		// Enter Role name
			cnp.editRoleName(roleName);
		System.out.println("New Role Name entered " + roleName);

		// Click on profile created
		x1 = "//select[@id='availList']/option[text()='" + profile_name + "']";
		driver.findElement(By.xpath(x1)).click();
		
		System.out.println("Profile Selected >>" + profile_name);

		// click Button ">>"
		
		cnp.addEntityToMemberList();

		// Verify Member list

					WebElement wb2 = 	cnp.getMemberList();
					
					expextedResult = profile_name;
					Select sel = new Select(wb2);
					List<WebElement> li = sel.getOptions();
					flag=false;
					for (WebElement wb3 : li) {
						if (expextedResult.equals(wb3.getText())) {
							System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
							flag = true;
							break;
						}
					}
					Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");


		// Click Save Button
		cnp.saveRole();
		
		System.out.println(" Save button Clicked ..");

		// Verify Role Created or not

		
			x1 = "//*[text()='" + roleName + "']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();
			
			Assert.assertTrue(status);
			System.out.println(" \n Role " + roleName + "  created >>pass !!!");

			
		/************ Create Another Profile to Select *****************/

		profile_new_name = profile_new_name + "_" + num.nextInt(100);
		System.out.println("Going to Create Profile... " + profile_new_name);

		

		/************ Navigate to CRM Settings *****************/
		hp.navigateToSettings();

		System.out.println("In CRM Settings");

		/************ Navigate to Profiles *****************/
		sp.navigateToProfiles();
		
		System.out.println("In Profiles");

		
			// Click on New Profile Button
		
			pp.navigateToNewProfiles();
		
			System.out.println("In Create New Profile");

			// Enter Profile Name
			
			cpp.getProfilenameEdit().sendKeys(profile_new_name);
		
			// Click On Next
			cpp.getNextbtn().click();

			System.out.println("In Create New Profile ...Next Clicked");
			// Click on Finish
			 wb=vap.getFinishbtn();
			wbUtil.waitForCompleteElementToLoad(wb);
			vap.getFinishbtn().click();
			
			System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_new_name);
		

		/************ Verify Profile Created *****************/

		x1 = "//*[text()='" + profile_new_name + "']";

		status = driver.findElement(By.xpath(x1)).isDisplayed();

		Assert.assertTrue(status);
		{

			System.out.println("Profile created.. " + profile_new_name);
		}


		/************ Navigate to CRM Settings *****************/
		
		hp.navigateToSettings();

		System.out.println("In CRM Settings");


		/************ Step 3 : Navigate to Roles *****************/
		
		sp.navigateToRoles();

		/************ Step 3.1 : Verify Roles displayed *****************/


		status=rp.getRolesHierarchyTreeText().isDisplayed();
			
		sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
		

		/************ Step 4 : Navigate to Editing Role *****************/

		// Move mouse cursor

		x1 = "//a[text()='" + roleName + "']";
		WebElement wb4 = driver.findElement(By.xpath(x1));
		wbUtil.waitForCompleteElementToLoad(wb4);
		Actions act1 = new Actions(driver);
		act1.moveToElement(wb4).perform();

		// click on Edit role image option

		x1 = "//a[text()='" + roleName + "']/../../td[2]/div/a[2]/img[@title='Edit Role']";
		WebElement wb5 = driver.findElement(By.xpath(x1));

		status = driver.findElement(By.xpath(x1)).isDisplayed();

		Assert.assertTrue(status);

		wbUtil.waitForCompleteElementToLoad(wb5);

		wb5.click();

		/************ Step 4.1 : Verify Editing Role displayed *****************/


		EditingRole ep=PageFactory.initElements(driver, EditingRole.class);
		wbUtil.waitForCompleteElementToLoad(ep.geteditingRoleElement());
		String s = 	ep.geteditingRoleElement().getText();

		status = s.contains("> Editing");

		sa.assertTrue(status);

		/************ Step 5 : Edit New Roles: *****************/

		// Identify the Profile and select it

		x1 = "//select[@id='availList']/option[text()='" + profile_new_name + "']";
		driver.findElement(By.xpath(x1)).click();

		// click Button ">>"
		cnp.addEntityToMemberList();
		System.out.println("Clicked on  '>>' button ");

		// Verify Member list has Profile
		WebElement wb21 = cnp.getMemberList();
		expextedResult = profile_new_name;
		Select sel1 = new Select(wb21);
		List<WebElement> list = sel1.getOptions();

		for (WebElement wb3 : list) {
			if (expextedResult.equals(wb3.getText())) {
				System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");
		
		// After moving newProfile,  select old Profile name from Member List to remove
		

		 wb2 = 	cnp.getMemberList();
		
		 sel = new Select(wb2);
		 sel.deselectAll();
		 sel.selectByVisibleText(profile_name);
		 
		// click Button "<<" to change /move existing profile

		cnp.getRemoveEntityFromMemberListbtn().click();
		
		
		
		System.out.println("Clicked on  '<<' button for " + profile_name);
		
		// Click Save Button
		cnp.saveRole();
		
		System.out.println(" Save button Clicked ..");
		
		
		

		/************ Step 5.1: Verify Roles page displayed *****************/

		status=rp.getRolesHierarchyTreeText().isDisplayed();
		
		sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
		
		// Verify Edited RoleName
		x1 = "//*[contains(text(),'" + roleName + "')]";

		status = driver.findElement(By.xpath(x1)).getText().contains(roleName);

		Assert.assertTrue(status);

		System.out.println(" \n Role " + roleName + "  After Edit created >>pass !!!");

		sa.assertAll();
	}


	

	@Test(priority = 5, groups = { "RegressionTest" })
	public void EditRoleWithAddingNewProfile()throws Throwable {


		/********** Test data **********/
		String x1, expextedResult, actualResult, expectedPage, profile_name, roleName, profile_new_name;
		boolean flag = false, status;

		// Read common from properties file
		FileUtils flib = new FileUtils();

		Random num = new Random();

		// Read common from Excel file

		roleName = flib.getExcelData("Sheet1", 2, 10);
		roleName = roleName + "_" + num.nextInt(100) + num.nextInt(100);

		profile_name = flib.getExcelData("Sheet1", 2, 11);
		profile_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);
		profile_new_name = profile_name + "_" + num.nextInt(100) + num.nextInt(100);
		/*--------------------------------------------*/

		/************ Create Profile to Select *****************/

		System.out.println("Going to Create Profile... " + profile_name);


		/************ Navigate to CRM Settings *****************/
		Home hp=PageFactory.initElements(driver, Home.class);
		hp.navigateToSettings();

		System.out.println("In CRM Settings");

		/************ Navigate to Profiles *****************/
		Settings sp=PageFactory.initElements(driver, Settings.class);
		sp.navigateToProfiles();
		
		System.out.println("In Profiles");

		
		// Click on New Profile Button
		
		Profiles pp=PageFactory.initElements(driver, Profiles.class);
		pp.navigateToNewProfiles();
		
		
		// wbUtil.waitForElementPresent(driver, wb);
	
		System.out.println("After Clicking on New Profile button");

		System.out.println("In Create New Profile");

		// Enter Profile Name
		
		ProfilesPrivileges cpp=PageFactory.initElements(driver, ProfilesPrivileges.class);
		cpp.getProfilenameEdit().sendKeys(profile_name);
		
		
		
		// Click On Next
		cpp.getNextbtn().click();
		
		System.out.println("In Create New Profile ...Next Clicked");

	
		
		// Click on Finish
		ViewingAccessPrivileges vap=PageFactory.initElements(driver, ViewingAccessPrivileges.class);
		
		WebElement wb=vap.getFinishbtn();
		wbUtil.waitForCompleteElementToLoad(wb);
		vap.getFinishbtn().click();
		
		
		System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_name);

		/************ Verify Profile Created *****************/

		x1 = "//*[text()='" + profile_name + "']";
		 status=driver.findElement(By.xpath(x1)).isDisplayed();
		
		Assert.assertTrue(status);
		{

			System.out.println("Profile created.. " + profile_name);
		} 
		

		

		/************ Create Role first *****************/

		/************ Navigate to CRM Settings *****************/
		
		hp.navigateToSettings();

		System.out.println("In CRM Settings");


		/************ Step 3 : Navigate to Roles *****************/
		
		sp.navigateToRoles();
		


		/************ Step 3.1 : Verify Roles displayed *****************/

		
		Roles rp=PageFactory.initElements(driver, Roles.class);
		status=rp.getRolesHierarchyTreeText().isDisplayed();
			
		
		sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
	
	

		/************ Step 4 : Navigate to Create New Roles *****************/

		// Move mouse cursor to Organisation
			
		WebElement wb1 = rp.getOrganisationRole();
		
		// wbUtil.waitForElementPresent(driver, wb1);

		

		Actions act = new Actions(driver);
		act.moveToElement(wb1).perform();

		System.out.println("after moving mouse cursor!");
		// click on Add role image option

		wb1 = rp.getOrganisationAddRoleMenu();

		wbUtil.waitForCompleteElementToLoad(wb1);
		wb1.click();
		System.out.println("After clicking on add role!");

		/************ Step 4.1 : Verify Create New Roles displayed *****************/

			x1 = "//*[text()=' > Create New Role']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();

			CreateNewRole cnp=PageFactory.initElements(driver, CreateNewRole.class);
			status=cnp.getCreateNewRolepage().isDisplayed();
			
			
			sa.assertTrue(status," Create New Roles  does not  opens >> Fail"); 
			
		

		/************ Step 5 : Create New Roles: *****************/

		// Enter Role name
			cnp.editRoleName(roleName);
		System.out.println("New Role Name entered " + roleName);

		// Click on profile created
		x1 = "//select[@id='availList']/option[text()='" + profile_name + "']";
		driver.findElement(By.xpath(x1)).click();
		
		System.out.println("Profile Selected >>" + profile_name);

		// click Button ">>"
		
		cnp.addEntityToMemberList();

		// Verify Member list

					WebElement wb2 = 	cnp.getMemberList();
					
					expextedResult = profile_name;
					Select sel = new Select(wb2);
					List<WebElement> li = sel.getOptions();
					flag=false;
					for (WebElement wb3 : li) {
						if (expextedResult.equals(wb3.getText())) {
							System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
							flag = true;
							break;
						}
					}
					Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");


		// Click Save Button
		cnp.saveRole();
		
		System.out.println(" Save button Clicked ..");

		// Verify Role Created or not

		
			x1 = "//*[text()='" + roleName + "']";
			status=driver.findElement(By.xpath(x1)).isDisplayed();
			
			Assert.assertTrue(status);
			System.out.println(" \n Role " + roleName + "  created >>pass !!!");

			
		/************ Create Another Profile to Select *****************/

		profile_new_name = profile_new_name + "_"+ num.nextInt(100);
		System.out.println("Going to Create Profile... " + profile_new_name);

		

		/************ Navigate to CRM Settings *****************/
		hp.navigateToSettings();

		System.out.println("In CRM Settings");

		/************ Navigate to Profiles *****************/
		sp.navigateToProfiles();
		
		System.out.println("In Profiles");

		
			// Click on New Profile Button
		
			pp.navigateToNewProfiles();
		
			System.out.println("In Create New Profile");

			// Enter Profile Name
			
			cpp.getProfilenameEdit().sendKeys(profile_new_name);
		
			// Click On Next
			cpp.getNextbtn().click();

			System.out.println("In Create New Profile ...Next Clicked");
			// Click on Finish
			 wb=vap.getFinishbtn();
			wbUtil.waitForCompleteElementToLoad(wb);
			vap.getFinishbtn().click();
			
			System.out.println("In Create New Profile ...Finish Clicked....created profile is " + profile_new_name);
		

		/************ Verify Profile Created *****************/

		x1 = "//*[text()='" + profile_new_name + "']";

		status = driver.findElement(By.xpath(x1)).isDisplayed();

		Assert.assertTrue(status);
		{

			System.out.println("Profile created.. " + profile_new_name);
		}


		/************ Navigate to CRM Settings *****************/
		
		hp.navigateToSettings();

		System.out.println("In CRM Settings");


		/************ Step 3 : Navigate to Roles *****************/
		
		sp.navigateToRoles();

		/************ Step 3.1 : Verify Roles displayed *****************/


		status=rp.getRolesHierarchyTreeText().isDisplayed();
			
		
		sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
	

		/************ Step 4 : Navigate to Editing Role *****************/

		// Move mouse cursor

		x1 = "//a[text()='" + roleName + "']";
		WebElement wb4 = driver.findElement(By.xpath(x1));
		wbUtil.waitForCompleteElementToLoad(wb4);
		Actions act1 = new Actions(driver);
		act1.moveToElement(wb4).perform();

		// click on Edit role image option

		x1 = "//a[text()='" + roleName + "']/../../td[2]/div/a[2]/img[@title='Edit Role']";
		WebElement wb5 = driver.findElement(By.xpath(x1));

		status = driver.findElement(By.xpath(x1)).isDisplayed();

		Assert.assertTrue(status);

		wbUtil.waitForCompleteElementToLoad(wb5);

		wb5.click();

		/************ Step 4.1 : Verify Editing Role displayed *****************/


		EditingRole ep=PageFactory.initElements(driver, EditingRole.class);
		wbUtil.waitForCompleteElementToLoad(ep.geteditingRoleElement());
		String s = 	ep.geteditingRoleElement().getText();

		status = s.contains("> Editing");

		sa.assertTrue(status,"Editing Role page  does not open >> Fail");

		/************ Step 5 : Edit New Roles: *****************/

		// Identify the Profile and select it

		x1 = "//select[@id='availList']/option[text()='" + profile_new_name + "']";
		driver.findElement(By.xpath(x1)).click();

		// click Button ">>"
		cnp.addEntityToMemberList();
		System.out.println("Clicked on  '>>' button ");

		// Verify Member list has Profile
		WebElement wb21 = cnp.getMemberList();
		expextedResult = profile_new_name;
		Select sel1 = new Select(wb21);
		List<WebElement> list = sel1.getOptions();

		for (WebElement wb3 : list) {
			if (expextedResult.equals(wb3.getText())) {
				System.out.println(" Verified success " + expextedResult + " shown in \"Members of List\"");
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag," Verified Fail" + expextedResult + " shown in \"Members of List\"");

		// Click Save Button
		cnp.saveRole();
		
		System.out.println(" Save button Clicked ..");
		
		
		
		/************ Step 5.1: Verify Roles page displayed *****************/

		status=rp.getRolesHierarchyTreeText().isDisplayed();
		
		sa.assertTrue(status,"Roles & Hierarchy Tree  does not opens >> Fail"); 
	

		// Verify Edited RoleName
		x1 = "//*[contains(text(),'" + roleName + "')]";

		status = driver.findElement(By.xpath(x1)).getText().contains(roleName);

		Assert.assertTrue(status);

		System.out.println(" \n Role " + roleName + "  After Edit created >>pass !!!");

		sa.assertAll();
	}


	}


