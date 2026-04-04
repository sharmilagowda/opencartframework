package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;
import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.CsvUtil;
import com.opencart.qa.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registrionPageSetUp() {
		registerPage=loginPage.doCliskRegister();
	}
	
	
@DataProvider
public Object[][] registrationData(){
	return new Object[][] { {"vidhya", "Devi", "81989898989", "vidya@123", "vidya@123", "true"},
		{"Ramya", "Devi", "81980098989", "vidya@123", "vidya@123", "false"},
		{"Sagar", "kumar", "80789898989", "vidya@123", "vidya@123", "true"}
	};
}


@DataProvider 
public Object[][] getRegistrationExcelData(){
	return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
}


@DataProvider 
public Object[][] getRegistrationCSVData(){
	return CsvUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
}
  @Test(dataProvider = "getRegistrationExcelData")
  public void userRegisterTest(String fname, String lName, String phonenumber, String password, String subscription) {
	  Assert.assertTrue(registerPage.userRegistation(fname,lName, phonenumber, password, subscription));
  }
}
