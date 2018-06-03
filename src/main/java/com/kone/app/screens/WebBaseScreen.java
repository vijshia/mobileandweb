package com.kone.app.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
}
