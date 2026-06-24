package com.opencart.qa.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	private Properties prop;
	
	public BrowserOptions(Properties prop) {
		this.prop=prop;
	}
	
	public ChromeOptions chromeOptions() {
		ChromeOptions co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			
			co.setCapability("browserName", "chrome");
		}
		return co;
	}

	
	public FirefoxOptions firefoxOptions() {
		FirefoxOptions fo= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
		}
		return fo;
	}
	public EdgeOptions edgeOptions() {
		EdgeOptions eo= new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("-inprivate");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
		}
		return eo;
	}
}
