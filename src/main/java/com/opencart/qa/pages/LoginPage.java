package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	//1.initialize driver and Utils
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2.page class construstor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. private By locators PO:
	
	private final By loginEmailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final  By forgotPassword = By.linkText("Forgotten Password");
	private final  By registerUser = By.linkText("Register");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By NewCustRegn = By.className("well");
	private final By continueBtn= By.linkText("Continue");
	private final By warningmessage= By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	
	//4. Login page methods/actions
	@Step("Getting login page title")
	public String  getLoginPageTitle() {
		return eleUtil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
	}
	
	public String  getLoginPageUrl() {
		return eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_URLVALUE,AppConstants.SHORT_TIME_OUT);
	}

	@Step("Checking forgot password link")
	public boolean isForgotPwdLinkExists() {
		return eleUtil.waitForVisiilityOfElement(forgotPassword, AppConstants.SHORT_TIME_OUT).isDisplayed();
	}
	
	@Step("Login to application with UserID:{0} and password{1}")
	public String doLoginwithInvalidUser(String userName, String pwd) {
		eleUtil.doSendKeys(loginEmailID, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String warninText = eleUtil.waitForPresenceOfElement(warningmessage, AppConstants.LONG_TIME_OUT).getText();
		return warninText;
	}
	public String domultipletimeLogin(String userName, String pwd) {
		eleUtil.waitForPresenceOfElement(loginEmailID, AppConstants.LONG_TIME_OUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String warninText = eleUtil.waitForPresenceOfElement(warningmessage, AppConstants.LONG_TIME_OUT).getText();
		return warninText;
	}
	public HomePage doLogin(String userName, String pwd) {
		
		eleUtil.waitForPresenceOfElement(loginEmailID, AppConstants.SHORT_TIME_OUT).sendKeys(userName);
		System.out.println("UserName : "+ userName + "Password :" + pwd);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new HomePage(driver);
	}
	
	public String isNewCustomerRegnExists() {
		return eleUtil.doGetText(NewCustRegn);
	}
	
	public boolean isContinueBtnExists() {
		return eleUtil.waitForVisiilityOfElement(continueBtn, AppConstants.SHORT_TIME_OUT).isDisplayed();
	}
	
	public RegistrationPage doCliskRegister() {
		eleUtil.doClick(registerUser);
	return new RegistrationPage(driver);
	}
	
	
}
