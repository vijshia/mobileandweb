package com.kone.app.screens.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.BaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SideMenuScreen extends BaseScreen{
	
public static AppiumDriver<MobileElement> driver;
    
    private By newTaskMenu = By.xpath("//*[@content-desc='note_addNew Task ']");
    private By logoutMenu = By.xpath("//*[@content-desc='exit_to_appLogout ']");
	
	public SideMenuScreen() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public LoginScreen clickLogoutMenu() {
		findElement(logoutMenu).click();
		LoginScreen loginScreen = new LoginScreen();
		Assert.assertTrue(loginScreen.isDisplayed(), "Failed to logout");
		return loginScreen;
	}
	
	public boolean isDisplayed() {
		return waitForElementPresent(newTaskMenu, 30) != null;
	}

}
