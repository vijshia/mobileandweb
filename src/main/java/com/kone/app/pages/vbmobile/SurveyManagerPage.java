package com.kone.app.pages.vbmobile;


import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class SurveyManagerPage extends PhoneBasePage {
	
    public static AppiumDriver<MobileElement> driver;
    
    private By menuButton = By.xpath("//*[contains(@aria-label,'Menu')]");
    private By spinner = By.xpath("//*[@class='md-half-circle']");
	
	public SurveyManagerPage() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Open side menu")
	public SideMenuPage openSideMenu(String mobileMenutoSelect) {
		
		driver.findElement(menuButton).click();
		SideMenuPage sideMenuScreen = new SideMenuPage();
		Assert.assertTrue(sideMenuScreen.
				isDisplayed(mobileMenutoSelect, DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open side menu");
		return sideMenuScreen;
	}
	
	@Step("Open survey id {0}")
	public SurveyPage openSurvey(String taskId) {
		
		By surveyTaskId = By.xpath("//span[text()='" + taskId + "']");
		waitForElementNotPresent(spinner, 100);
		waitForElementClickable(surveyTaskId, 60);
		driver.findElement(surveyTaskId).click();
		waitForElementNotPresent(surveyTaskId, DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT);
		SurveyPage surveyScreen = new SurveyPage();
		Assert.assertTrue(surveyScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open survey id " + taskId);
		return surveyScreen;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(menuButton, timeout) != null;
	}

}
