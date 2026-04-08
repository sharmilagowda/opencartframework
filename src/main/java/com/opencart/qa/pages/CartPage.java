package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CartPage {
	
	
	// intialize drivers & utils
	private WebDriver driver;
	private ElementUtil eUtil ;
	// added this line from main branch again addd
	
	
	// create constructor 
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
		
	}
	
	
// private locators
	
	
		private final By itemscount = By.xpath("//div[@class='table-responsive']//img");
		private final By productName= By.xpath("(//div[@class='table-responsive']//tr)[2]//td[@class='text-left']/a");
		private final By productModel = By.cssSelector(".text-left");
		private final By productRewardPoints = By.xpath("(//div[@class='table-responsive']//tr)[2]//small");
		private final By unitPrice =  By.cssSelector(".text-right");
		private final By totalPrice =  By.cssSelector(".text-right");
		
		
		
		/**
		 * get the count of items in the cart
		 * @return count
		 */
	
			public  int getitemsCount() {
		 
				return eUtil.waitForAllElementsVisible(itemscount, AppConstants.MEDIUM_TIME_OUT).size();
				}
			
			
			public void getproductdata() {
				
			String productname =eUtil.getElement(productName).getText();
			
			String prodrewardsPoint =driver.findElement(with(productRewardPoints).below(productName)).getText();
			String prodmodel =driver.findElement(with(productModel).toRightOf(productName)).getText();
			String prodUnitprice = driver.findElement(with(unitPrice).toRightOf(productName)).getText();
			String prodtotalprice = driver.findElement(with(totalPrice).toRightOf(unitPrice).toRightOf(productName)).getText();
			
			System.out.println(productname);
				
			System.out.println(prodrewardsPoint);
			System.out.println(prodmodel); 
			System.out.println(prodUnitprice);
			System.out.println(prodtotalprice);
			}

}
