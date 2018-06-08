package com.kone.app.screens;

import java.lang.reflect.Field;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

public class PhoneBaseScreen {
	
	public static AndroidDriver<MobileElement> driver;
	
	public PhoneBaseScreen() {
	
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
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
