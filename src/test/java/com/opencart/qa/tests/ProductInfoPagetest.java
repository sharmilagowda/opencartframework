package com.opencart.qa.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.opencart.qa.base.BaseTest;

public class ProductInfoPagetest extends BaseTest {
	
	
	@BeforeClass
	public void productInfoPageSetUp() {
		homepage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	@DataProvider
	public Object[][] getProductData(){
			return new Object[][] {
				{"macbook","MacBook Pro"},
				{"macbook", "MacBook Air"},
				{"imac", "iMac" },	
				{"samsung", "Samsung SyncMaster 941BW"},
				{"samsung", "Samsung Galaxy Tab 10.1"},
				{"canon", "Canon EOS 5D"}
				};
	}
	
  @Test(dataProvider ="getProductData")
  public void productheaderTest(String searchKey, String productName) {
	 resultspage = homepage.doSearch(searchKey);
	 productinfopage= resultspage.selectProduct(productName);
	  Assert.assertEquals(productinfopage.getprductheader(), productName);
  }
  
  @DataProvider
	public Object[][] getProductDataCount(){
			return new Object[][] {
				{"macbook","MacBook Pro", 4},
				{"macbook", "MacBook Air",4},
				{"imac", "iMac", 3 },	
				{"samsung", "Samsung SyncMaster 941BW",1 },
				{"samsung", "Samsung Galaxy Tab 10.1",7},
				{"canon", "Canon EOS 5D",3}
				};
	}
  @Test(dataProvider = "getProductDataCount")
  public void productCountTest(String searchKey, String productName, int count) {
	  		resultspage = homepage.doSearch(searchKey);
		 productinfopage= resultspage.selectProduct(productName);
		 Assert.assertEquals(productinfopage.getProductImagesCount(), count);
  }
  
  @DataProvider
  public Object[][] getProductMetaData() {

      Map<String, String> map1 = new TreeMap<>();
      map1.put("Brand", "Apple");
      map1.put("Availability", "Out Of Stock");
      map1.put("totalimages", "4");
      map1.put("Product Code", "Product 18");
      map1.put("productname", "MacBook Pro");
      map1.put("Reward Points", "800");
      map1.put("productPrice", "$2,000.00");
      map1.put("productPriceWithTax", "$2,000.00");
      map1.put("searchgkey", "macbook");
      map1.put("searchproductname", "MacBook Pro");
      	return new Object[][] { { map1 } };
  }
  
  @DataProvider
  public Object[][] getProductData1() {
	  return new Object[][] {{"macbook","MacBook Pro"}};
  }

  
  @Test(dataProvider="getProductMetaData")
  public void productInfoDataTest(Map<String, String> mapdata) {
	  	resultspage = homepage.doSearch(mapdata.get("searchgkey"));
		 productinfopage= resultspage.selectProduct(mapdata.get("searchproductname"));
		 mapdata.remove("searchgkey");
		 mapdata.remove("searchproductname");
		 Map<String,String> productInfoMap = productinfopage.getProductInfoData();
		 Assert.assertEquals(productInfoMap, mapdata);
//		SoftAssert softAssert =new SoftAssert();
//		
//		
//		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
//		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
//		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
//		softAssert.assertEquals(productInfoMap.get("productPrice"), "$2,000.00");
//		softAssert.assertAll();
		
		
		System.out.println(productInfoMap);
  }
}
