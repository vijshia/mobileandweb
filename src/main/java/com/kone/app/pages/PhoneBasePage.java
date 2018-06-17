package com.kone.app.pages;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidKeyMetastate;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ru.yandex.qatools.allure.annotations.Step;

public class PhoneBasePage {
	
	public static AndroidDriver<MobileElement> driver;
	
	protected By searchButton = By.xpath("//md-icon[text()='search']");
	protected static final long DEFAULT_WAIT_ELEMENT_TIMEOUT = 10;
	protected static final long DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT = 30;
	
	public PhoneBasePage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Wait for {0} seconds")
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void typeByPressKeyCode(String text) {
		
		for(char c : text.toCharArray()) {
			int key;
			String code = "";
			int metaState = 0;

			if(c == '@') {
				code = "AT";
			} else if (c == '.') {
				code = "PERIOD";
			} else if(c == '_') {
				code = "MINUS";
				metaState = AndroidKeyMetastate.META_SHIFT_ON;
			}  else {
				code = ("" + c).toUpperCase();
				if (Character.isUpperCase(c)) {
					metaState = AndroidKeyMetastate.META_SHIFT_ON;
				}
			}
			
			try {
				Field f = AndroidKeyCode.class.getField("KEYCODE_" + code);
				key = f.getInt(null);

				if (metaState != 0) {
					driver.pressKeyCode(key, metaState);
				} else {
					driver.pressKeyCode(key);
				}				
			} catch(Exception e) {}
			
		}		
	}
	
	public MobileElement findElement(By by) {
	    
		System.out.println("Find element: " + by);
		MobileElement ele;
		
		try {
			ele = driver.findElement(by);
		} catch (NoSuchElementException e) {
			Dimension size = driver.manage().window().getSize();
		    int X = size.width / 2;
		    int topY = (int) (size.height * .3);		    
		    int bottomY = (int) (size.height * .7);
		    
		    //Swipe up
		    for(int i=0; i<2; i++) {
		    	TouchAction action = new TouchAction(driver);
		    	action.press(PointOption.point(X, topY))
		    	      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		    	      .moveTo(PointOption.point(X, bottomY))
		    	      .release()
		    	      .perform();
		    	
		    	try {
		    		ele = driver.findElement(by);
		    		return ele;
		    	} catch (NoSuchElementException ex) {}
		    }
		    
		    //Swipe down
		    for(int i=0; i<10; i++) {
		    	TouchAction action = new TouchAction(driver);
		    	action.press(PointOption.point(X, bottomY))
		    	      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		    	      .moveTo(PointOption.point(X, topY))
		    	      .release()
		    	      .perform();
		    	
		    	try {
		    		ele = driver.findElement(by);
		    		return ele;
		    	} catch (NoSuchElementException ex) {}
		    }
		}	
		
		return (MobileElement) driver.findElement(by);
	}
	
	@Step("Scroll the page to find element {0}")
	public boolean scrollToElement(By by) {
		
		// Note: TouchAction is supported only in NATIVE_APP mode
		String context = driver.getContext();
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			driver.context("NATIVE_APP");
			Dimension size = driver.manage().window().getSize();
			
		    int X = size.width / 2;
		    int topY = (int) (size.height * .3);		    
		    int bottomY = (int) (size.height * .7);
		    
		    //Swipe up
		    for(int i=0; i<2; i++) {
		    	driver.context("NATIVE_APP");
		    	TouchAction action = new TouchAction(driver);
		    	action.press(PointOption.point(X, topY))
		    	      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		    	      .moveTo(PointOption.point(X, bottomY))
		    	      .release()
		    	      .perform();
		    	
		    	try {
		    		driver.context(context);
		    		driver.findElement(by);
		    		return true;
		    	} catch (NoSuchElementException ex) {}
		    }
		    
		    //Swipe down
		    for(int i=0; i<10; i++) {
		    	driver.context("NATIVE_APP");
		    	TouchAction action = new TouchAction(driver);
		    	action.press(PointOption.point(X, bottomY))
		    	      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		    	      .moveTo(PointOption.point(X, topY))
		    	      .release()
		    	      .perform();
		    	
		    	try {
		    		driver.context(context);
		    		driver.findElement(by);
		    		return true;
		    	} catch (NoSuchElementException ex) {}
		    }
		}
		
		if(driver.getContext() != context) {
			driver.context(context);
		}
		return false;
	}
	
	@Step("Check if an element present: {0} - Timeout: {1} seconds")
    public boolean isElementPresent(By by, long seconds) {
    	
    	Log.info("Check if an element present: " + by + " / With Timeout: " + seconds);
    	
    	try {
    		WebDriverWait wait = new WebDriverWait(driver, seconds);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(by));	
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
    }
	
	@Step("Wait for element present: {0} - Timeout: {1} seconds")
    public MobileElement waitForElementPresent(By by, long seconds) {
    	
    	Log.info("Wait for element present: " + by + " / With Timeout: " + seconds);
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    	return driver.findElement(by);
    }
    
	@Step("Wait for element Not present: {0} - Timeout: {1} seconds")
    public void waitForElementNotPresent(By by, long seconds) {
    	
    	Log.info("Wait for element Not present: " + by + " / With Timeout: " + seconds);
    	WebDriverWait wait = new WebDriverWait(driver, seconds);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
