package com.kone.app.pages.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class SideMenuPage extends PhoneBasePage{
	
public static AppiumDriver<MobileElement> driver;
    
    private By newTaskMenu = By.xpath("//*[text()='New Task']");
    private By tasksMenu = By.xpath("//*[text()='Tasks']");
    private By logoutMenu = By.xpath("//*[text()='Logout']");
	
	public SideMenuPage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Click Logout from side menu")
	public LoginPage logout() {
		
		driver.findElement(logoutMenu).click();
		LoginPage loginScreen = new LoginPage();
		Assert.assertTrue(loginScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to logout");
		return loginScreen;
	}
	
	@Step("Open Tasks from side menu")
	public TasksPage openTasks() {
		
		driver.findElement(tasksMenu).click();
		TasksPage tasksScreen = new TasksPage();
		Assert.assertTrue(tasksScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open Tasks");
		return tasksScreen;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(newTaskMenu, timeout) != null;
	}

}
