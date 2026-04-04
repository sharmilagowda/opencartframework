package com.opencart.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opencart.qa.exceptions.BrowserException;
import com.opencart.qa.exceptions.FrameworkException;



public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highlight;
	BrowserOptions browserOptions;
	
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
/**
 * This method will initialize the driver on the basis of given browsername and launch URL
 * @param browserName
 * @return driver
 */
	public WebDriver initDriver(Properties prop) {
		String browserName= prop.getProperty("browser");
		highlight=prop.getProperty("highlight");
		browserOptions = new BrowserOptions(prop);
		System.out.println("Browser nmae: " + browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(browserOptions.chromeOptions()));
			//driver = new ChromeDriver(browserOptions.chromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(browserOptions.firefoxOptions()));
			//driver = new FirefoxDriver(browserOptions.firefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(browserOptions.edgeOptions()));
			//driver = new EdgeDriver(browserOptions.edgeOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver = new SafariDriver();
			
			break;
		default:
			System.out.println("The browser you have netered is not valid :" + browserName);
			throw new BrowserException("---Invalid Browser---");

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
	/**
	 * this will returns one local copy of Driver to a thread
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	/**
	 * This methods is used to  initialize the Properties file for different environments
	 * @return it reutuns the Object of Properties class which is having all properties(key-value format)
	 */
	
	// MVN gives the property  -Denv="qa"
	public Properties initProp() {
		 String envName = System.getProperty("env");
		 FileInputStream ip =null;
		 prop= new Properties();
		 
		 try {
			 if( envName == null) {
				 System.out.println("Environment is null..  hence executing scripts on QA ");
				 ip=new FileInputStream("./src/test/resources/Config/config.qa.properties");
			 }
			 else {
				 
			
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip=new FileInputStream("./src/test/resources/Config/config.qa.properties");
				break;
			case "dev":
				ip=new FileInputStream("./src/test/resources/Config/config.dev.properties");
				break;
			case "stage":
				ip=new FileInputStream("./src/test/resources/Config/config.stage.properties");
				break;
			case "prod":
				ip=new FileInputStream("./src/test/resources/Config/config.properties");
				break;
			default:
				System.out.println("==== Invalid Environment... please enter correct one +++++" + envName);
				throw new FrameworkException("Invaid environement Name");
				
			}
			
			 }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		return((TakesScreenshot) getDriver()).getScreenshotAs((OutputType.FILE));// temp dir
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
