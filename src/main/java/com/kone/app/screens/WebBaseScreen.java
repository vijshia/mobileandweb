package com.kone.app.screens;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.screens.konesitesurvey.SiteHomeScreen.lnk_selectPlannedTypes;
import static com.kone.app.screens.salesforce.SelectOpportunityScreen.lnk_SearchedOpportunity;
import static com.kone.app.screens.outlook.OutlookLoginScreen.btn_Sign;

public class WebBaseScreen {

	public static WebDriver wdriver;
	private By Replaced_Xpath;

	public WebBaseScreen() {
		wdriver = WebContext.wdriver;
	}

	@Step("Wait for element present: {0} - Timeout: {1} seconds")
	public WebElement waitForElementPresent(By by, long seconds) {

		Log.info("Wait for element present: " + by + " / With Timeout: " + seconds);
		WebDriverWait wait = new WebDriverWait(wdriver, seconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return wdriver.findElement(by);
	}

	@Step("Wait for element Not present: {0} - Timeout: {1} seconds")
	public WebElement waitForElementNotPresent(By by, int seconds) {

		Log.info("Wait for element Not present: " + by + " / With Timeout: " + seconds);
		WebDriverWait wait = new WebDriverWait(wdriver, seconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		return wdriver.findElement(by);
	}

	@Step("Wait for element Not present: {0} - Timeout: {1} seconds")
	public WebElement waitForElementtobeClickable(By by, int seconds) {

		Log.info("Wait for element to be Clickable: " + by + " / With Timeout: " + seconds);
		WebDriverWait wait = new WebDriverWait(wdriver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return wdriver.findElement(by);
	}
	
	@Step("Wait for Presence of Element Located: {0} - Timeout: {1} seconds")
	public WebElement waitForpresenceOfElementLocated(By by, int seconds) {

		Log.info("Wait for Presence of Element Located: " + by + " / With Timeout: " + seconds);
		WebDriverWait wait = new WebDriverWait(wdriver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return wdriver.findElement(by);
	}
	
/*	@Step("Wait for element present stalenessOf: {0} - Timeout: {1} seconds")
	public WebElement waitForElementPresentstalenessOf(By by, int seconds) {

		Log.info("Wait for element present stalenessOf: " + by + " / With Timeout: " + seconds);
		WebDriverWait wait = new WebDriverWait(wdriver, seconds);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until((ExpectedConditions.stalenessOf(element)));
		return wdriver.findElement(by);
	}*/
	
	@Step("Entering value in: {0}")
	public WebElement enteringPassword(By by, String value) {

		Log.info("Entering Passwrod in the text field " + by);
		wdriver.findElement(by).sendKeys(value);
		return wdriver.findElement(by);
	}

	@Step("Entering value in: {0} - value: {1}")
	public WebElement enteringValueinTextField(By by, String value) {

		Log.info("Entering Value in the text field " + by + " / with the value" + value);
		wdriver.findElement(by).sendKeys(value);
		return wdriver.findElement(by);
	}

	@Step("Clicking on the Element: {0}")
	public WebElement clickonButton(By by) {

		Log.info("Click action performed in the link or button " + by);
		wdriver.findElement(by).click();
		return null;
	}

	@Step("Select on the Element: {0} - value: {1}")
	public WebElement selectElementbyVisibleText(By by, String value) {

		Log.info("Selecting Value in from the dropdown field " + by);
		WebElement toSelect = wdriver.findElement(by);
		Select valuetoSelect = new Select(toSelect);
		valuetoSelect.selectByVisibleText(value);
		return wdriver.findElement(by);
	}

	@Step("Get Currnt URL: ")
	public String gettingCurrentURL() {

		try {
			Log.info("Getting Current URL");
			String url = wdriver.getCurrentUrl();
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Step("Get Text: {0}")
	public String gettingText(By by) {

		try {
			Log.info("Getting Text from the attribute " + by);
			String text = wdriver.findElement(by).getText();
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Step("Getting Text from List: {0}")
	public List<String> gettingTextfromList(By by) {
		List<WebElement> localvariable = null;
		List<String> Values = new LinkedList<>();

		try {
			Log.info("Getting WebElements to getText " + by);
			localvariable = wdriver.findElements(by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (WebElement Value : localvariable) {
			Values.add(Value.getText());
		}
		Log.info("Returning List of retrived Text " + by);
		return Values;
	}

	@Step("Get Attribute by ClassName: {0}")
	public String gettingAttributebyClass(By by) {

		try {
			Log.info("Getting Attribute from the ClassName " + by);
			// String changedattribute="\"" + attribute + "\"";
			String getattribute = wdriver.findElement(by).getAttribute("class");
			return getattribute;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Step("Get WebElement: {0}")
	public WebElement gettingWebElement(By by) {

		try {
			Log.info("Getting WebElement " + by);
			WebElement getwebelement = wdriver.findElement(by);
			return getwebelement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Step("Get WebElements from List: {0}")
	public List<WebElement> gettingWebElementsfromList(By by) {
		List<WebElement> localvariable = null;
		try {
			Log.info("Getting List of WebElements " + by);
			localvariable = wdriver.findElements(by);
			return localvariable;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Step("Page Scroll Down using Robot:")
	public WebElement scrollDownRobot() {

		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Step("Page Scroll Down using Robot:")
	public WebElement scrollUpRobot() {

		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_UP);
			robot.keyRelease(KeyEvent.VK_PAGE_UP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Step("Page Scroll Down using JavaScript:")
	public WebElement scrollDownJavaScript() {

		try {
			JavascriptExecutor js = (JavascriptExecutor) wdriver;
			js.executeScript("scroll(0, 250);");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Step("Page Scroll Up using JavaScript:")
	public WebElement scrollUpJavaScript() {

		try {
			JavascriptExecutor js = (JavascriptExecutor) wdriver;
			js.executeScript("scroll(0, -250);");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Step("Converting userdata to Xpath key Contains : {0}")
	public By stringtoXpathContains(String To_Replaced) {
		try {
			String str3 = lnk_selectPlannedTypes.toString();
			String Value_Replaced = str3.replace("Replace", To_Replaced);
			String Full_Replaced = Value_Replaced.replace("By.xpath: //", "//");
			Replaced_Xpath = By.xpath(Full_Replaced);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Replaced_Xpath;
	}

	@Step("Converting userdata to Xpath_SearchedOpportunity: {0}")
	public By stringtoXpathSearchedOpportunity(String To_Replaced) {
		try {
			String str3 = lnk_SearchedOpportunity.toString();
			String Value_Replaced = str3.replace("Replace", To_Replaced);
			String Full_Replaced = Value_Replaced.replace("By.xpath: //", "//");
			Replaced_Xpath = By.xpath(Full_Replaced);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Replaced_Xpath;
	}
	
	@Step("Converting userdata to Xpath key Contains : {0}")
	public By stringtoXpathEquals(String To_Replaced) {
		try {
			String str3 = btn_Sign.toString();
			String Value_Replaced = str3.replace("Replace", To_Replaced);
			String Full_Replaced = Value_Replaced.replace("By.xpath: //", "//");
			Replaced_Xpath = By.xpath(Full_Replaced);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Replaced_Xpath;
	}
	
}
