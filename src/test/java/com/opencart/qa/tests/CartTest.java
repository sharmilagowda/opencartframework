package com.opencart.qa.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;

public class CartTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		homepage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
  @Test
  public void f() {
  }
}
