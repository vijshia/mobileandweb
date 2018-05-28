package com.kone.app.screens.vbmobile;


import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.BaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MainScreen extends BaseScreen {
	
    public static AppiumDriver<MobileElement> driver;
    
    private By serveyManagerTitle = By.xpath("//*[@content-desc='Survey Manager']");
    private By menuButton = By.xpath("//*[@content-desc='SideMenu']");
	
	public MainScreen() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public SideMenuScreen openSideMenu() {
		findElement(menuButton).click();
		SideMenuScreen sideMenuScreen = new SideMenuScreen();
		Assert.assertTrue(sideMenuScreen.isDisplayed(), "Failed to open side menu");
		return sideMenuScreen;
	}
	
	public boolean isDisplayed() {
		return waitForElementPresent(serveyManagerTitle, 5) != null;
	}

}
