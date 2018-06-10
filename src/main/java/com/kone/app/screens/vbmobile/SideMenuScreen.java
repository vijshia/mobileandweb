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
    private By tasksMenu = By.xpath("//*[text()='Tasks']");
    private By logoutMenu = By.xpath("//*[text()='Logout']");
	
	public SideMenuScreen() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Click Logout from side menu")
	public LoginScreen logout() {
		
		driver.findElement(logoutMenu).click();
		LoginScreen loginScreen = new LoginScreen();
		Assert.assertTrue(loginScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to logout");
		return loginScreen;
	}
	
	@Step("Open Tasks from side menu")
	public TasksScreen openTasks() {
		
		driver.findElement(tasksMenu).click();
		TasksScreen tasksScreen = new TasksScreen();
		Assert.assertTrue(tasksScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open Tasks");
		return tasksScreen;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(newTaskMenu, timeout) != null;
	}

}
