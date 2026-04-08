package com.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class HomePage {

	
	//# initialize drivers & utilities
	 WebDriver driver;
	 ElementUtil eleUtil;
	 
	//# HomePage constructor 
	 public HomePage(WebDriver driver) {
		 this.driver=driver;
		 eleUtil = new ElementUtil(driver);
	 }
	 
	 //#  define the page By Locators(private)
	 
	 private final By logout= By.linkText("Logout");
	 private final By headers = By.cssSelector("div#content>h2");
	 private final By searchtext = By.name("search");
	 private final By searchImg =By.xpath("//div[@id='search']//button[@type='button']");
	 private final By cart = By.xpath("//div[@id='cart']/button[@type='button']");
	 private final By checkout = By.linkText("View Cart");
	 
	 
	 
	 //methods/actions (public)
	 
	 public String getHomePageTitle() {
		 return eleUtil.waitForTittleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
	 }
	 
	 public boolean isLogoutLinkExist() {
		return eleUtil.isDisplayed(logout);
		 
	 }
	 
	 public List<String> getHomePageHearders() {
		List<WebElement> eleList =eleUtil.waitForAllElementsPresence(headers, AppConstants.SHORT_TIME_OUT);
		List<String> headerList = new ArrayList<String>();
		for(WebElement e:eleList) {
			String text = e.getText();
			headerList.add(text);
		}
		return headerList;
	 }
	 
	 public SearchResultPage doSearch(String searchKey) {
		 System.out.println("Searching for the product :" + searchKey);
		 eleUtil.doSendKeys(searchtext, searchKey, AppConstants.SHORT_TIME_OUT);
		 eleUtil.doClick(searchImg);
		 return new SearchResultPage(driver);
		 
	 } //TDD -> Test driven development
	 
	 public CartPage clickcart() {
		 eleUtil.doClick(cart);
		 eleUtil.waitForElementReadyandclick(checkout, AppConstants.MEDIUM_TIME_OUT);
		 return new CartPage(driver);
		 
	 }
	
}
