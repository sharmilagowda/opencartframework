package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class SearchResultPage {
	
	// webdriver & element Util
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	// SearchResultPage constructor
	public SearchResultPage(WebDriver driver) {
		this.driver =driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	//By locators(private)
	private final By searchResults=By.cssSelector("div.product-thumb");



	
	//Methods/actions (public)
	public int doGetSearchResults() {
		int searchResultCount=eleUtil.waitForAllElementsVisible(searchResults, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("total number of products:" + searchResultCount);
		return searchResultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Select the product : " + productName );
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
	
}
