package com.opencart.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	static WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//Purple
		for (int i = 0; i < 7; i++) {
			changeColor("rgb(0,200,0)", element);// Green
			changeColor(bgcolor, element);// Purple
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title").toString();
	}

	public String getURLByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.URL").toString();
	}

	public void generateAlert(String message) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
	}
	
	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("history.go(0)").toString();
	}
	
	public void navigateToBackPageByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("history.go(-1)").toString();
	}
	
	public void navigateToForwardPageByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("history.go(-1)").toString();
	}
	
	public void navigateToSpecificPageByJS(String pageNumber) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("history.go('"+pageNumber+"')").toString();
	}
	
	
	public String getInnerTextByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	

}
