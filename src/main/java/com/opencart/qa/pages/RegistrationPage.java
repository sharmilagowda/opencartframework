package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;
import com.opencart.qa.utils.StringUtil;

public class RegistrationPage {
	
	//1.initialize driver and Utils
		private WebDriver driver;
		private ElementUtil eleUtil;

		
		//2.page class construstor
		public RegistrationPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		
		// 3. private By locators 
		private final By fName =By.id("input-firstname");
		private final By lName =By.id("input-lastname");
		private final By telePhone =By.id("input-telephone");
		private final By emailId =By.id("input-email");
		private final By password =By.id("input-password");
		private final By confPassword =By.id("input-confirm");
		private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
		private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
		private final By privacyPolicy =By.name("agree");
		private final By contn= By.xpath("//input[@type='submit' and @value='Continue']");
		private final By successMessage = By.cssSelector("div#content h1");
		private final By logoutLink= By.linkText("Logout");
		private final By registrationLink = By.linkText("Register");
		
		//Logout
		
		
		
	//4. Behavior/actions
		public boolean userRegistation(String firstName, String lastName, String telephone,  String pasword1, String subscription) {
			eleUtil.waitForPresenceOfElement(fName, AppConstants.SHORT_TIME_OUT).sendKeys(firstName);
			eleUtil.doSendKeys(lName, lastName);
			eleUtil.doSendKeys(this.telePhone, telephone);
			eleUtil.doSendKeys(this.emailId, StringUtil.generateEmailId());
			eleUtil.doSendKeys(password, pasword1);
			eleUtil.doSendKeys(confPassword, pasword1);
			
			if(Boolean.parseBoolean(subscription)) {
				eleUtil.doClick(subscribeYes);
			}
			else {
				eleUtil.doClick(subscribeNo);
			}	
			eleUtil.doClick(privacyPolicy);
			eleUtil.doClick(contn);
			
			
			
		
			String text=eleUtil.doGetText(successMessage);
			
			if(eleUtil.waitForPresenceOfElement(successMessage, AppConstants.MEDIUM_TIME_OUT).getText().contains(AppConstants.REG_PAGE_SUCCESSMSG)) {
				eleUtil.doClick(logoutLink);
				eleUtil.doClick(registrationLink);
			
				return true;
			}
			
			return false;
		}
		

}
