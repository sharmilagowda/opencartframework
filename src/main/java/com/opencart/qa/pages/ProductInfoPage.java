package com.opencart.qa.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class ProductInfoPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	private Map<String, String> productMap;
	
	// ProductInfoPage constructor
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}



	//By locator
	private final By productImages= By.cssSelector("ul.thumbnails img");
	private final By productHeader = By.cssSelector("div#content h1");
	private final By quantity =By.xpath("//input[@name='quantity']");
	private final By addToCart = By.id("button-cart");
	private final By metaData =By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private final By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	
	// methods/behavior 
	
	public String getprductheader() {
		return eleUtil.doGetText(productHeader);
	}
	
	
	public int getProductImagesCount() {
		return eleUtil.waitForAllElementsVisible(productImages, AppConstants.SHORT_TIME_OUT).size();
	}
	
	public Map<String,String> getProductInfoData() {
		productMap=new TreeMap<String,String>();
		productMap.put("productname", getprductheader());
		productMap.put("totalimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	private void getProductMetaData() {
		
	List<WebElement> metalist = eleUtil.getElements(metaData);
		for(WebElement e:metalist) {
			String metaText = e.getText();
			String meta[] =metaText.split(":");
			String key =meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
			
		}
	}
	
	private void getProductPriceData() {
	
	List<WebElement> pricelist = eleUtil.getElements(priceData);
	
			String productPrice = pricelist.get(0).getText().trim();
			String productPriceWithTax = pricelist.get(1).getText().split(":")[1].trim();
			
			
		//	System.out.println(productPricetax);
			//String productPriceWithTax =productPricetax[1].trim();

			productMap.put("productPrice", productPrice);
			productMap.put("productPriceWithTax", productPriceWithTax);
		
	}
	

}
