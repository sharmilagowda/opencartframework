package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;

public class CartTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		homepage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
  @Test
  public void testItemsCount() {
	  cartPage= homepage.clickcart();
	 Assert.assertEquals(cartPage.getitemsCount(),3);
  }
  
  
  @Test
  public void getProductdetails() {
	  cartPage= homepage.clickcart();
	  cartPage.getproductdata();
  }
}
