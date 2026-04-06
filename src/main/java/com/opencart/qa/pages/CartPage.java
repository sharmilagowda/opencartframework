package com.opencart.qa.pages;

import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.ElementUtil;

public class CartPage {
	
	private WebDriver driver;
	private ElementUtil eUtil ;
	// added this line from main branch
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
		
	}
	
	
	
	
	

}
