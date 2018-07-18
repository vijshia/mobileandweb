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
    
    public static By menutoSelect = By.xpath("//*[text()='Replace']");
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
	
	@Step("Open Survey Manager from side menu")
	public CreateTaskinSurveyManagerPage surveyManager(String mobileMenutoSelect) {
		
		driver.findElement(stringtoXpathEquals(mobileMenutoSelect)).click();
		
		CreateTaskinSurveyManagerPage Createtaskinsurveymanagerpage = new CreateTaskinSurveyManagerPage();
		Assert.assertTrue(Createtaskinsurveymanagerpage.isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open Survey Manager");
		return Createtaskinsurveymanagerpage;
	}
	
	public boolean isDisplayed(String mobileMenutoSelect, long timeout) {
		return waitForElementPresent(stringtoXpathEquals(mobileMenutoSelect), timeout) != null;
	}

}
