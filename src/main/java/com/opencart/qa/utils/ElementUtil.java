package com.opencart.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencart.qa.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act= new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * this method will locate the element & returns WebElement
	 * 
	 * @param locator
	 * @return
	 */

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		
		return element;

	}
	
	/**
	 * GETELEMET WITH WAIT IMPLEMETED
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	
	public  WebElement getElement(By locator, long timeout) {
		
		try {
			return driver.findElement(locator);
			
		} catch (NoSuchElementException e) {
			System.out.println("can not find the element with + " + locator);
			System.out.println(e);
			return waitForVisiilityOfElement(locator, timeout);
		}
		
	}

	/**
	 * this method will locate the elements and &returns List<WebElement>
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	/**
	 * This method will display the size of the WebElements List
	 * 
	 * @param locator
	 * @return
	 */
	public int findSize(By locator) {
		return getElements(locator).size();
	}
	
	
	public void doClearData(By locator) {
		getElement(locator).clear();
	}
	/**
	 * this method will send the keys with locator information.
	 * 
	 * @param locator
	 * @param value
	 */
	@Step("Sending text data using locator{0} and string value{1}")
	public void doSendKeys(By locator, String value) {
		doClearData(locator);
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(By locator, String value, int time) {
		doClearData(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(value);
		
	}
	/**
	 * This method will get the text from the element and return the text
	 * 
	 * @param locator
	 * @return
	 */

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttributeData(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}
	
	/**
	 * this method will perform click action on the element
	 */

	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * this method used to validate whether element id displayed in the given page
	 */

	public boolean isDisplayed(By locator) {

		try {
			return getElement(locator).isDisplayed();

		} catch (NoSuchElementException e) {
			System.out.println("NO such element found... please check the locator");
			e.printStackTrace();
			return false;
		}

	}

	// ********************FindElements**********************************//

	/**
	 * this method will returns List<string> of data from WebElements list collected
	 * through FindElements
	 * 
	 * @param locator
	 * @return
	 */

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();// vc=10;pc=0 ; []

		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}

		return eleTextList;
	}

//****************************** Select Tag DropDrown Options Utils  ***********************//

	/**
	 * This method will create a Select object
	 * 
	 * @param locator
	 * @return
	 */

	public Select getSelect(By locator) {
		return new Select(getElement(locator));
	}

	/**
	 * This method will select the drop down option using Index value passed
	 * 
	 * @param locator
	 * @param index
	 */

	public void doDropDownSelectionByIndex(By locator, int index) {
		getSelect(locator).selectByIndex(index);
	}

	/**
	 * This method will select the drop down option using Text value passed
	 * 
	 * @param locator
	 * @param text
	 */

	public void doDropDownSelectionByText(By locator, String text) {
		getSelect(locator).selectByVisibleText(text);
	}

	/**
	 * This method will select the drop down option using value passed
	 * 
	 * @param locator
	 * @param value
	 */

	public void doDropDownSelectionByValue(By locator, String value) {
		getSelect(locator).selectByValue(value);
	}

	/**
	 * This method will give the count of options available in a drop down selection
	 * 
	 * @param locator
	 * @return
	 */
	public int getDropDownOptionsCount(By locator) {
		List<WebElement> optionsList = getSelect(locator).getOptions();
		return optionsList.size();
	}

	/**
	 * This method will give the first option selected in the drop down(single/multi
	 * select)
	 * 
	 * @param locator
	 * @return
	 */
	public String getFirstSelectedOptions(By locator) {
		WebElement ele = getSelect(locator).getFirstSelectedOption();
		return ele.getText();
	}

	/**
	 * This method will give the all options available in a drop down selection
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownOptions(By locator) {
		List<WebElement> Optionslist = getSelect(locator).getOptions();
		List<String> optionsTextList = new ArrayList<String>();// VC=10 Pc=0
		for (WebElement e : Optionslist) {
			optionsTextList.add(e.getText());
		}
		return optionsTextList;
	}

	/**
	 * This method will gives the List<String> of all options selected data in the
	 * drop down selection
	 * 
	 * @param locator
	 * @return
	 */

	public List<String> getSelectedata(By locator) {

		List<WebElement> slectedoptionslist = getSelect(locator).getAllSelectedOptions();
		List<String> textList = new ArrayList<String>();
		for (WebElement e : slectedoptionslist) {
			String text = e.getText();
			textList.add(text);

		}
		return textList;
	}

	// *************************Dropdown options selection without select
	// class*******************//

	/**
	 * This method will select the drop down options. This method will handle 3
	 * usecases #1 single selection "Choice 2" #2 multi selection "choice 2",
	 * "choice 3", "choice 2 1" #3 all -for this case we need to pass "all" as
	 * parameter
	 * 
	 * @param locator
	 * @param value
	 */

	public void doOptionSelection(By locator, String... value) {
		List<WebElement> choiceList = driver.findElements(locator);

		if (value[0].trim().toLowerCase() == "all") {
			for (WebElement e : choiceList) {
				e.click();
			}

		} else {
			for (WebElement e : choiceList) {
				String text = e.getText();
				for (String choice : value) {
					if (text.trim().toLowerCase().equals(choice)) {
						e.click();
						break;
					}
				}
			}

		}

	}

//**********************************Actions class menu handling *********************************************//
	
	
	
	/**
	 * this method will handle- 2 menu items 
	 * @param parentLocator
	 * @param childLocator
	 */

	public void handleMenuItemsLevel2(By parentLocator, By childLocator) {
		act.moveToElement(getElement(parentLocator)).click().perform();
		doClick(childLocator);

	}
	
	/**
	 * 
	 * @param menu1
	 * @param menu2
	 * @param menu3
	 * @param menu4
	 * @throws InterruptedException
	 */
	
	public  void handleMenuItemsLevel4(By menu1, By menu2,By menu3, By menu4) throws InterruptedException {
		doClick(menu1);
		act.moveToElement(getElement(menu2)).perform();
		Thread.sleep(1000);
		act.moveToElement(getElement(menu3)).perform();

		Thread.sleep(1000);
		doClick(menu4);
		
	}
	
	/**
	 * This method will works same as Sendkeys
	 * @param locator
	 * @param value
	 */
	
	public void doActionsSendKeys(By locator,String value) {
		act.sendKeys(getElement(locator), value).perform();
	}
	
	
	/**
	 * this method works same as click
	 * @param locator
	 */
	
	public void doActionsClick(By locator) {
		act.click(getElement(locator)).perform();
	}
	
	
	
	/**
	 * This method with send keys with pause to any text field
	 * @param locator
	 * @param text
	 * @param pauseTime
	 */
	public  void doSendKeysWithPause(By locator, String text, int pauseTime) {
		char value[]=text.toCharArray();
		
		for(char e:value) {
			act.sendKeys(getElement(locator),  String.valueOf(e))
			.pause(500)
			.perform();
			
		}
		
	}
	
	
	
	//***************************************Wait Util*************************************************//
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public List<WebElement> waitForAllElementsPresence(By locator, long timeout){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			//List<WebElement> list=	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerLocator));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForAllElementsVisible(By locator, long timeout){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			
			return Collections.emptyList();
		}
	
			
	}
	
	
	
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeout
	 */
	public void waitForElementReadyandclick(By locator, long timeout) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	
	public void waitForElementReadyandclick(By locator, long timeout,long pollingTime) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait
		.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(NoSuchElementException.class)
		.withMessage("------Element ot found with locator-----" + locator);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	
	
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
		This does not necessarily mean that the element is visible.
	 * @param locator
	 * @param timeframe
	 * @return
	 */
	public  WebElement waitForPresenceOfElement(By locator, long timeframe) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeframe));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	
	public  WebElement waitForPresenceOfElement(By locator, long timeframe, long pollingTime) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeframe));
		wait
		.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(NoSuchElementException.class)
		.withMessage("------Element not found with locator-----" + locator);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}


	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	  	Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeframe
	 * @return
	 */

	
	public  WebElement waitForVisiilityOfElement(By locator, long timeframe) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeframe));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public  WebElement waitForVisiilityOfElement(By locator, long timeframe, long pollingTime) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeframe));
		wait
		.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(NoSuchElementException.class)
		.withMessage("------Element ot found with locator-----" + locator);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	
	/**
	 *  An expectation for checking that an element is present on the DOM of a page and visible using Fluent Wait.
	 * @param locator
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	
	public WebElement waitForVisibilityOfElementWithFluentWait(By locator, long timeout, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.withMessage("------Element ot found with locator-----" + locator);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	
	
	/**
	 * An expectation for checking that the title contains a case-sensitive substring
	 * @param time
	 * @param title
	 */
	
	public  String waitForTitleContains(String title,int time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println(title + "not found");
			e.printStackTrace();
			return driver.getTitle();
			
		}	

	}
	
	
	/**
	 * An expectation for checking the title of a page.
	 * @param time
	 * @param title
	 * @return
	 */
	public  String waitForTittleIs(String title,int time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println(title + "not found");
			e.printStackTrace();
			return driver.getTitle();
			
		}	

	}
	
	
	/**
	 * An expectation for the URL of the current page to contain specific text.
	 * @param time
	 * @param urlTitle
	 * @return
	 */
	
	public  String waitForUrlContains(String urlTitle,int time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
			wait.until(ExpectedConditions.urlContains(urlTitle));
			
		} catch (TimeoutException e) {
			System.out.println(urlTitle + "not found");
			e.printStackTrace();		
		}
		return driver.getCurrentUrl();
	}
	
	/**
	 * Expectation for the URL to match a specific regular expression
	 * @param time
	 * @param urlTitle
	 * @return
	 */
	
	public  String waitForUrlMatches(String urlTitle,int time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		
		try {
			wait.until(ExpectedConditions.urlMatches(urlTitle));
			
		} catch (TimeoutException e) {
			System.out.println(urlTitle + "not found");
			e.printStackTrace();
		}
		return driver.getCurrentUrl();	
	}
	
	
	
	/**
	 * wait for the alert and accept
	 * @param timeout
	 */
	
	public  void waitForAlertAccept(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		String alertText =alert.getText();
		alert.accept();
		System.out.println(alertText);
	}
	public  void waitForAlertDismiss(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		String alertText =alert.getText();
		alert.accept();
		System.out.println(alertText);
	}
	
	public  void waitForPromptAlertAccept(int timeout, String text) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(text);
		String alertText =alert.getText();
		alert.accept();
		System.out.println(alertText);
	}
	
	
	public  void waitForFrameAndSwitchtoIt(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
	}
	
	public  void waitForFrameAndSwitchtoIt(int frameIndex, int timeout ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		
	}
	
	public  void waitForFrameAndSwitchtoIt(String frameIdORName, int timeout ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdORName));
		
	}
	
	public  void waitForFrameAndSwitchtoIt(WebElement frameEle, int timeout ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameEle));
	}
	
	
}
