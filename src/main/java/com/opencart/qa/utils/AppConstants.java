package com.opencart.qa.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE= "Account Login11";
	public static final String LOGIN_PAGE_URLVALUE= "route=account/login";
	public static final String LOGIN_PAGE_URL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";
	public static final String HOME_PAGE_TITLE= "My Account";
	public static final String LOGIN_PAGE_NEWCUSTOMERBLC= "New Customer";
	public static final List<String> HOME_PAGE_HEADERS_LIST =  Arrays.asList("My Account", "My Orders", "My Affiliate Account","Newsletter");
	public static final String LOGIN_PAGE_WARNING = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	public static final String LOGIN_PAGE_ALERT="Warning: No match for E-Mail Address and/or Password.";
	public static final String REG_PAGE_SUCCESSMSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
	
	public static final int SHORT_TIME_OUT=5;
	public static final int MEDIUM_TIME_OUT=10;
	public static final int LONG_TIME_OUT=15;
}
