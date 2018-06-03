package com.kone.app.screens.vbmobile;


import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.PhoneBaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class MainScreen extends PhoneBaseScreen {
	
    public static AppiumDriver<MobileElement> driver;
    
    private By menuButton = By.xpath("//*[@aria-label='SideMenu']");
	
	public MainScreen() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Open side menu")
	public SideMenuScreen openSideMenu() {
		
		driver.findElement(menuButton).click();
		SideMenuScreen sideMenuScreen = new SideMenuScreen();
		Assert.assertTrue(sideMenuScreen.isDisplayed(), "Failed to open side menu");
		return sideMenuScreen;
	}
	
	public boolean isDisplayed() {
		return waitForElementPresent(menuButton, 30) != null;
	}

}
