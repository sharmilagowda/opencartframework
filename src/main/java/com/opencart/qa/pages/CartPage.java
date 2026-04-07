package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class CartPage {
	
	private WebDriver driver;
	private ElementUtil eUtil ;
	// added this line from main branch again addd
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
		
	}
	


}
