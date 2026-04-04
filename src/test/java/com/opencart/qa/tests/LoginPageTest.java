package com.opencart.qa.tests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.opencart.qa.base.BaseTest;
import com.opencart.qa.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Opencart Login Page")
@Story("Login page functionalities")
public class LoginPageTest extends BaseTest {
  
	@Description("Pagetitle test")
	@Severity(SeverityLevel.NORMAL)

	@Test
	public void loginPagetitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(),AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	
	@Description("Pagetitle test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		ChainTestListener.log("loginPage Test");
		Assert.assertTrue(loginPage.getLoginPageUrl().contains(AppConstants.LOGIN_PAGE_URLVALUE));
	}
	
	@Test
	public void forgotPwdlinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void appLoginTest() {
		homepage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(homepage.getHomePageTitle(),AppConstants.HOME_PAGE_TITLE);
	}
	@Test
	public void newCustRegnBLKTest() {
		
		String newcustblk =loginPage.isNewCustomerRegnExists();
		System.out.println(newcustblk);
		Assert.assertTrue(newcustblk.contains(AppConstants.LOGIN_PAGE_NEWCUSTOMERBLC));
	}
	@Test
	public void continueBtnLinkTest() {
		Assert.assertTrue(loginPage.isContinueBtnExists());
	}
	
	
//	//Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.
//	@DataProvider
//	public Object[][] invalidLoginData() {
//	
//		return new Object[][] { {"sharma24","pwd1"}, {"sharma24","pwd2"},{"sharma24","pwd3"},{"sharma24","pwd3"},};
//	}
//	
//	@Test(dataProvider ="invalidLoginData")
//	public void invalidUserTest(String userName, String password) {
//		Assert.assertEquals(loginPage.doLoginwithInvalidUser(userName, password), AppConstants.LOGIN_PAGE_ALERT);
//	}
//	
//	@DataProvider
//	public Object[][] invalidLogiMultipletime() {
//	
//		return new Object[][] { {"sharma24","pwd3"}};
//	}
//	
//	@Test(dataProvider ="invalidLogiMultipletime",dependsOnMethods ="invalidUserTest")
//	public void multipleLoginWithInvalidUserTest(String userName, String password) {
//		Assert.assertEquals(loginPage.doLoginwithInvalidUser(userName, password), AppConstants.LOGIN_PAGE_WARNING);
//	}
}
