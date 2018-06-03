package com.kone.app.screens.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.PhoneBaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class SideMenuScreen extends PhoneBaseScreen{
	
public static AppiumDriver<MobileElement> driver;
    
    private By newTaskMenu = By.xpath("//*[text()='New Task']");
    private By logoutMenu = By.xpath("//*[text()='Logout']");
	
	public SideMenuScreen() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Click logout from side menu")
	public LoginScreen clickLogoutMenu() {
		
		driver.findElement(logoutMenu).click();
		LoginScreen loginScreen = new LoginScreen();
		Assert.assertTrue(loginScreen.isDisplayed(), "Failed to logout");
		return loginScreen;
	}
	
	public boolean isDisplayed() {
		return waitForElementPresent(newTaskMenu, 30) != null;
	}

}
