package com.kone.app.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;

import ru.yandex.qatools.allure.annotations.Step;

public class WebBaseScreen {
	
	public static WebDriver wdriver;
	
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
	
	@Step("Entering value in: {0} - value: {1}")
    public WebElement enteringValueinTextField(By by, String value) {
		Log.info("Entering Value in the text field "+ by + " / with the value"+value);
		wdriver.findElement(by).sendKeys(value);
		return wdriver.findElement(by);		
	}
	
	@Step("Clicking on the Element: {0}")
    public WebElement clickonButton(By by) {
		Log.info("Click action performed in the link or button "+ by);
		wdriver.findElement(by).click();
		return null;		
	}
	
	@Step("Select on the Element: {0} - value: {1}")
    public WebElement selectElementbyVisibleText(By by, String value) {
		Log.info("Selecting Value in from the dropdown field "+ by);
		WebElement toSelect=wdriver.findElement(by);
		Select valuetoSelect=new Select(toSelect);
		valuetoSelect.selectByVisibleText(value);
		return wdriver.findElement(by);		
	}
	
	@Step("Get Currnt URL: ")
    public String gettingCurrentURL() {
		Log.info("Getting Current URL");
		String url=wdriver.getCurrentUrl();
		return url;		
	}
	
	@Step("Get Text: {0}")
    public String gettingText(By by) {
		Log.info("Getting Text from the attribute "+by);
		String text=wdriver.findElement(by).getText();
		return text;		
	}
}
