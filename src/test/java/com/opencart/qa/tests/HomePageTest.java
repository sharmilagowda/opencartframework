package com.opencart.qa.tests;

import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;
import com.opencart.qa.utils.AppConstants;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.List;

import org.testng.Assert;


public class HomePageTest extends BaseTest{
  

	
  @BeforeClass
  public void beforeClass() {
	  homepage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	  
  }
  
  @Test
  public void homePageTitleTest() {
	  Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
	  
  }
  
  @Test
  public void isLogOutLinkExistsTest() {
	  Assert.assertTrue(homepage.isLogoutLinkExist());
  }

  @Test
  public void getHomePageHeaderList() {
	  List<String> hpHeadersList =homepage.getHomePageHearders();
	  Assert.assertEquals(hpHeadersList, AppConstants.HOME_PAGE_HEADERS_LIST);
  } 
  
  
  @DataProvider
  public Object[][] getSearchResultData() {

      return new Object[][] {
          {"macbook",3},
          {"imac",1},
          {"canon", 1},
          {"samsung", 2},
          {"airtel", 0}
      };
  }
  
 
 @Test(dataProvider = "getSearchResultData",priority = Integer.MAX_VALUE)
 public void doSearchProducts(String searchKey, int count) {
    resultspage=  homepage.doSearch(searchKey);
    Assert.assertEquals(resultspage.doGetSearchResults(), count);
	 
 }

}
