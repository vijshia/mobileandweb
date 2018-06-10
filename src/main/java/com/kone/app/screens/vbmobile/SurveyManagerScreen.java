package com.kone.app.screens.vbmobile;


import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.PhoneBaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class SurveyManagerScreen extends PhoneBaseScreen {
	
    public static AppiumDriver<MobileElement> driver;
    
    private By menuButton = By.xpath("//*[@aria-label='SideMenu']");
	
	public SurveyManagerScreen() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Open side menu")
	public SideMenuScreen openSideMenu() {
		
		driver.findElement(menuButton).click();
		SideMenuScreen sideMenuScreen = new SideMenuScreen();
		Assert.assertTrue(sideMenuScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open side menu");
		return sideMenuScreen;
	}
	
	@Step("Open survey id {0}")
	public SurveyScreen openSurveyById(String taskId) {
		
		By surveyTaskId = By.xpath("//span[text()='" + taskId + "']");
		driver.findElement(surveyTaskId).click();
		SurveyScreen surveyScreen = new SurveyScreen();
		Assert.assertTrue(surveyScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open survey id " + taskId);
		return surveyScreen;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(menuButton, timeout) != null;
	}

}
